package com.segurosbolivar.avaluos.modelo.service.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;

import org.apache.log4j.Logger;

public class InputStreamDataSource implements DataSource {

	private static final Logger log = Logger.getLogger(InputStreamDataSource.class);

	private static final int BUFFER_STREAM = 1024;
	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	private final String name;

	public InputStreamDataSource(InputStream inputStream, String name) {
		this.name = name;
		try {
			int reader;

			byte[] data = new byte[BUFFER_STREAM];
			while ((reader = inputStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, reader);
			}
			inputStream.close();
			buffer.flush();
		} catch (IOException ex) {
			log.error(ex);
			return;
		}
	}

	@Override
	public String getContentType() {
		return new MimetypesFileTypeMap().getContentType(name);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(buffer.toByteArray());
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		throw new IOException("Read-Only-Data");
	}

}
