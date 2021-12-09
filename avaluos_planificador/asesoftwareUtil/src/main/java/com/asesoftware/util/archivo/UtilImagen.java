package com.asesoftware.util.archivo;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.asesoftware.util.archivo.vo.ImageTransform;

public class UtilImagen {

	private UtilImagen() {
	}

	public static byte[] rotar90GradosDerecha(byte[] archivo) throws IOException {
		BufferedImage imagen = convertir(archivo);
		imagen = rotartDerecha(imagen, 90d);
		return convertir(imagen);
	}

	public static byte[] rotar90GradosIzquierda(byte[] archivo) throws IOException {
		BufferedImage imagen = convertir(archivo);
		imagen = rotartDerecha(imagen, -90d);
		return convertir(imagen);
	}

	public static BufferedImage convertir(byte[] archivo) throws IOException {
		BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(archivo));
		BufferedImage source = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
		source.getGraphics().drawImage(imagen, 0, 0, null);
		return source;
	}

	public static byte[] convertir(BufferedImage imagen) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(imagen, "jpg", baos);
		return baos.toByteArray();
	}

	public static BufferedImage rotartDerecha(BufferedImage imagen, double grados) {
		BufferedImage destinationImage;
		ImageTransform imTransform = new ImageTransform(imagen.getHeight(), imagen.getWidth());
		imTransform.rotate(grados);
		imTransform.findTranslation();
		AffineTransformOp ato = new AffineTransformOp(imTransform.getTransform(), AffineTransformOp.TYPE_BILINEAR);
		destinationImage = ato.createCompatibleDestImage(imagen, imagen.getColorModel());
		ato.filter(imagen, destinationImage);
		return destinationImage;
	}
}
