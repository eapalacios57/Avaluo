package com.segurosbolivar.avaluos.modelo.service.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Date;
import java.util.Iterator;
 
import org.apache.commons.io.IOUtils;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.bcpg.PublicKeyAlgorithmTags;
import org.bouncycastle.bcpg.sig.KeyFlags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPCompressedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedData;
import org.bouncycastle.openpgp.PGPEncryptedDataGenerator;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignature;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPSignatureList;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.PGPSignatureSubpacketVector;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.PBESecretKeyDecryptor;
import org.bouncycastle.openpgp.operator.PGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPBESecretKeyDecryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.bc.BcPGPDataEncryptorBuilder;
import org.bouncycastle.openpgp.operator.bc.BcPGPDigestCalculatorProvider;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyDataDecryptorFactory;
import org.bouncycastle.openpgp.operator.bc.BcPublicKeyKeyEncryptionMethodGenerator;

/**
 * PGP Util para encriptar y desencriptar archivos.
 * 
 * @author LuisContreras
 * @version 1.0
 */
public class PGPUtils {
 
    private static final int   BUFFER_SIZE = 1 << 16; // should always be power of 2
    private static final int   KEY_FLAGS = 27;
    private static final int[] MASTER_KEY_CERTIFICATION_TYPES = new int[]{
        PGPSignature.POSITIVE_CERTIFICATION,
        PGPSignature.CASUAL_CERTIFICATION,
        PGPSignature.NO_CERTIFICATION,
        PGPSignature.DEFAULT_CERTIFICATION
    };
 
    @SuppressWarnings("unchecked")
    public static PGPPublicKey readPublicKey(InputStream in)
        throws IOException, PGPException
    {
 
        PGPPublicKeyRingCollection keyRingCollection = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(in));
        PGPPublicKey publicKey = null;
        Iterator<PGPPublicKeyRing> rIt = keyRingCollection.getKeyRings();
 
        while (publicKey == null && rIt.hasNext()) {
            PGPPublicKeyRing kRing = rIt.next();
            Iterator<PGPPublicKey> kIt = kRing.getPublicKeys();
            while (publicKey == null && kIt.hasNext()) {
                PGPPublicKey key = kIt.next();
                if (key.isEncryptionKey()) {
                    publicKey = key;
                }
            }
        }
 
        if (publicKey == null) {
            throw new IllegalArgumentException("No se puede obtener la llave publica.");
        }
        if (!isForEncryption(publicKey)) {
            throw new IllegalArgumentException("KeyID " + publicKey.getKeyID() + " no marcado para cifrado.");
        }
 
        return publicKey;
    }
 
    @SuppressWarnings("unchecked")
    public static PGPSecretKey readSecretKey(InputStream in)
        throws IOException, PGPException
    {
 
        PGPSecretKeyRingCollection keyRingCollection = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(in));
        PGPSecretKey secretKey = null;
 
        Iterator<PGPSecretKeyRing> rIt = keyRingCollection.getKeyRings();
        while (secretKey == null && rIt.hasNext()) {
            PGPSecretKeyRing keyRing = rIt.next();
            Iterator<PGPSecretKey> kIt = keyRing.getSecretKeys();
            while (secretKey == null && kIt.hasNext()) {
                PGPSecretKey key = kIt.next();
                if (key.isSigningKey()) {
                    secretKey = key;
                }
            }
        }
 
        if (secretKey == null) {
            throw new IllegalArgumentException("No se puede obtener la llave privada.");
        }
        if (!secretKey.isSigningKey()) {
            throw new IllegalArgumentException("La llave privada no permite ser firmada.");
        }
        if (secretKey.getPublicKey().isRevoked()) {
            throw new IllegalArgumentException("La llave privada ha sido rechazada.");
        }
        if (!hasKeyFlags(secretKey.getPublicKey(), KeyFlags.SIGN_DATA)) {
            throw new IllegalArgumentException("La llave no puede ser usada para cifrar.");
        }
 
        return secretKey;
    }
 
    public  static PGPPrivateKey findPrivateKey(InputStream keyIn, long keyID, char[] pass)
        throws IOException, PGPException, NoSuchProviderException
    {
        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
        return findPrivateKey(pgpSec.getSecretKey(keyID), pass);
 
    }
 
    public static PGPPrivateKey findPrivateKey(PGPSecretKey pgpSecKey, char[] pass)
        throws PGPException
    {
        if (pgpSecKey == null) return null;
 
        PBESecretKeyDecryptor decryptor = new BcPBESecretKeyDecryptorBuilder(new BcPGPDigestCalculatorProvider()).build(pass);
        return pgpSecKey.extractPrivateKey(decryptor);
    }
 
    @SuppressWarnings("unchecked")
    public static void decryptFile(InputStream in, OutputStream out, InputStream keyIn, char[] passwd)
        throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());
 
        in = org.bouncycastle.openpgp.PGPUtil.getDecoderStream(in);
 
        PGPObjectFactory pgpF = new PGPObjectFactory(in);
        PGPEncryptedDataList enc;
 
        Object o = pgpF.nextObject();
        
        if (o instanceof  PGPEncryptedDataList) {
            enc = (PGPEncryptedDataList) o;
        } else {
            enc = (PGPEncryptedDataList) pgpF.nextObject();
        }

        Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
        PGPPrivateKey sKey = null;
        PGPPublicKeyEncryptedData pbe = null;
 
        while (sKey == null && it.hasNext()) {
            pbe = it.next();
 
            sKey = findPrivateKey(keyIn, pbe.getKeyID(), passwd);
        }
 
        if (sKey == null) {
            throw new IllegalArgumentException("Llave secreta para mensaje no encontrada.");
        }
 
        InputStream clear = pbe.getDataStream(new BcPublicKeyDataDecryptorFactory(sKey));
 
        PGPObjectFactory plainFact = new PGPObjectFactory(clear);
 
        Object message = plainFact.nextObject();
 
        if (message instanceof  PGPCompressedData) {
            PGPCompressedData cData = (PGPCompressedData) message;
            PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream());
 
            message = pgpFact.nextObject();
        }
 
        if (message instanceof  PGPLiteralData) {
            PGPLiteralData ld = (PGPLiteralData) message;
 
            InputStream unc = ld.getInputStream();
            int ch;
 
            while ((ch = unc.read()) >= 0) {
                out.write(ch);
            }
        } else if (message instanceof  PGPOnePassSignatureList) {
            throw new PGPException("El mensaje cifrado contiene un mensaje firmado, no datos literales.");
        } else {
            throw new PGPException("El mensaje no es un simple archivo encriptado - tipo desconocido.");
        }
 
        if (pbe.isIntegrityProtected()) {
            if (!pbe.verify()) {
                throw new PGPException("Mensaje de verificación de integridad fallida");
            }
        }
    }
 
    public static void encryptFile(
        OutputStream out,
        String fileName,
        PGPPublicKey encKey,
        boolean armor,
        boolean withIntegrityCheck)
        throws IOException, NoSuchProviderException, PGPException
    {
        Security.addProvider(new BouncyCastleProvider());
 
        if (armor) {
            out = new ArmoredOutputStream(out);
        }
 
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PGPCompressedDataGenerator comData = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);
 
        PGPUtil.writeFileToLiteralData(
                comData.open(bOut),
                PGPLiteralData.BINARY,
                new File(fileName) );
 
        comData.close();
 
        BcPGPDataEncryptorBuilder dataEncryptor = new BcPGPDataEncryptorBuilder(PGPEncryptedData.TRIPLE_DES);
        dataEncryptor.setWithIntegrityPacket(withIntegrityCheck);
        dataEncryptor.setSecureRandom(new SecureRandom());
 
        PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(dataEncryptor);
        encryptedDataGenerator.addMethod(new BcPublicKeyKeyEncryptionMethodGenerator(encKey));
 
        byte[] bytes = bOut.toByteArray();
        OutputStream cOut = encryptedDataGenerator.open(out, bytes.length);
        cOut.write(bytes);
        cOut.close();
        out.close();
    }
 
    @SuppressWarnings("unchecked")
    public static void signEncryptFile(
        OutputStream out,
        String fileName,
        PGPPublicKey publicKey,
        PGPSecretKey secretKey,
        String password,
        boolean armor,
        boolean withIntegrityCheck )
        throws Exception
    {
 
        Provider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
 
        if (armor) {
            out = new ArmoredOutputStream(out);
        }
 
        BcPGPDataEncryptorBuilder dataEncryptor = new BcPGPDataEncryptorBuilder(PGPEncryptedData.TRIPLE_DES);
        dataEncryptor.setWithIntegrityPacket(withIntegrityCheck);
        dataEncryptor.setSecureRandom(new SecureRandom());
 
        PGPEncryptedDataGenerator encryptedDataGenerator = new PGPEncryptedDataGenerator(dataEncryptor);
        encryptedDataGenerator.addMethod(new BcPublicKeyKeyEncryptionMethodGenerator(publicKey));
 
        OutputStream encryptedOut = encryptedDataGenerator.open(out, new byte[PGPUtils.BUFFER_SIZE]);
 
        PGPCompressedDataGenerator compressedDataGenerator = new PGPCompressedDataGenerator(PGPCompressedData.ZIP);
        OutputStream compressedOut = compressedDataGenerator.open(encryptedOut, new byte [PGPUtils.BUFFER_SIZE]);
 
        PGPPrivateKey privateKey = findPrivateKey(secretKey, password.toCharArray());
 
        PGPContentSignerBuilder signerBuilder = new BcPGPContentSignerBuilder(secretKey.getPublicKey().getAlgorithm(),
                HashAlgorithmTags.SHA1);
 
        PGPSignatureGenerator signatureGenerator = new PGPSignatureGenerator(signerBuilder);
        signatureGenerator.init(PGPSignature.BINARY_DOCUMENT, privateKey);
 
        boolean firstTime = true;
        Iterator<String> it = secretKey.getPublicKey().getUserIDs();
        while (it.hasNext() && firstTime) {
            PGPSignatureSubpacketGenerator spGen = new PGPSignatureSubpacketGenerator();
            spGen.setSignerUserID(false, it.next());
            signatureGenerator.setHashedSubpackets(spGen.generate());
            firstTime = false;
        }
        signatureGenerator.generateOnePassVersion(false).encode(compressedOut);
 
        PGPLiteralDataGenerator literalDataGenerator = new PGPLiteralDataGenerator();
        OutputStream literalOut = literalDataGenerator.open(
            compressedOut,
            PGPLiteralData.BINARY,
            fileName,
            new Date(),
            new byte [PGPUtils.BUFFER_SIZE] );
 
        FileInputStream in = new FileInputStream(fileName);
        byte[] buf = new byte[PGPUtils.BUFFER_SIZE];
        int len;
        while ((len = in.read(buf)) > 0) {
            literalOut.write(buf, 0, len);
            signatureGenerator.update(buf, 0, len);
        }
 
        in.close();
        literalDataGenerator.close();
        signatureGenerator.generate().encode(compressedOut);
        compressedDataGenerator.close();
        encryptedDataGenerator.close();
        if (armor) {
            out.close();
        }
    }
 
    public static boolean verifyFile(
        InputStream in,
        InputStream keyIn,
        String extractContentFile)
        throws Exception
    {
        in = PGPUtil.getDecoderStream(in);
 
        PGPObjectFactory pgpFact = new PGPObjectFactory(in);
        PGPCompressedData c1 = (PGPCompressedData)pgpFact.nextObject();
 
        pgpFact = new PGPObjectFactory(c1.getDataStream());
 
        PGPOnePassSignatureList p1 = (PGPOnePassSignatureList)pgpFact.nextObject();
 
        PGPOnePassSignature ops = p1.get(0);
 
        PGPLiteralData p2 = (PGPLiteralData)pgpFact.nextObject();
 
        InputStream dIn = p2.getInputStream();
 
        IOUtils.copy(dIn, new FileOutputStream(extractContentFile));
 
        int ch;
        PGPPublicKeyRingCollection pgpRing = new PGPPublicKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
 
        PGPPublicKey key = pgpRing.getPublicKey(ops.getKeyID());
 
        FileOutputStream out = new FileOutputStream(p2.getFileName());
 
        ops.init(new BcPGPContentVerifierBuilderProvider(), key);
 
        while ((ch = dIn.read()) >= 0)
        {
            ops.update((byte)ch);
            out.write(ch);
        }
 
        out.close();
 
        PGPSignatureList p3 = (PGPSignatureList)pgpFact.nextObject();
        return ops.verify(p3.get(0));
    }
    
    public static boolean isForEncryption(PGPPublicKey key)
    {
        if (key.getAlgorithm() == PublicKeyAlgorithmTags.RSA_SIGN
            || key.getAlgorithm() == PublicKeyAlgorithmTags.DSA
            || key.getAlgorithm() == PublicKeyAlgorithmTags.EC
            || key.getAlgorithm() == PublicKeyAlgorithmTags.ECDSA)
        {
            return false;
        }
 
        return hasKeyFlags(key, KeyFlags.ENCRYPT_COMMS | KeyFlags.ENCRYPT_STORAGE);
    }
 
    @SuppressWarnings("unchecked")
    private static boolean hasKeyFlags(PGPPublicKey encKey, int keyUsage) {
        if (encKey.isMasterKey()) {
            for (int i = 0; i != PGPUtils.MASTER_KEY_CERTIFICATION_TYPES.length; i++) {
                for (Iterator<PGPSignature> eIt = encKey.getSignaturesOfType(PGPUtils.MASTER_KEY_CERTIFICATION_TYPES[i]); eIt.hasNext();) {
                    PGPSignature sig = eIt.next();
                    if (!isMatchingUsage(sig, keyUsage)) {
                        return false;
                    }
                }
            }
        }
        else {
            for (Iterator<PGPSignature> eIt = encKey.getSignaturesOfType(PGPSignature.SUBKEY_BINDING); eIt.hasNext();) {
                PGPSignature sig = eIt.next();
                if (!isMatchingUsage(sig, keyUsage)) {
                    return false;
                }
            }
        }
        return true;
    }
 
    private static boolean isMatchingUsage(PGPSignature sig, int keyUsage) {
        if (sig.hasSubpackets()) {
            PGPSignatureSubpacketVector sv = sig.getHashedSubPackets();
            if (sv.hasSubpacket(PGPUtils.KEY_FLAGS)) {
                // code fix suggested by kzt (see comments)
                if (sv.getKeyFlags() == 0 && keyUsage == 0) {
                    return false;
                }
            }
        }
        return true;
    }
 
}