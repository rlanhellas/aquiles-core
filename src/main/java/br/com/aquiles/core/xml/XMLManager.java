package br.com.aquiles.core.xml;

import org.apache.commons.io.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class XMLManager {

	private static Unmarshaller prepareUnmarshaller(Class clazz) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		return jaxbContext.createUnmarshaller();
	}

	private static Marshaller prepareMarshaller(Class clazz) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		return marshaller;
	}

	/**
	 * Método estático para leitura de arquivo XML
	 * @param clazz Classe do Objeto root definido pelo XSD de leitura do arquivo
	 * @param file Objeto java.io.File que a classe deve ler
	 * @return
	 * @throws JAXBException
	 */
	public static Object readXMLFile(Class clazz, File file) throws JAXBException {
		Unmarshaller unmarshaller = prepareUnmarshaller(clazz);
		Object rootTag = unmarshaller.unmarshal(file);
		return rootTag;
	}

	public static Object readXMLString(Class clazz, String xmlText) throws JAXBException{
		Unmarshaller unmarshaller = prepareUnmarshaller(clazz);
		return unmarshaller.unmarshal(IOUtils.toInputStream(xmlText, Charset.defaultCharset()));
	}

	public static void writeXMLFile(Class clazz, Object rootElement, File file) throws JAXBException {
		Marshaller marshaller = prepareMarshaller(clazz);
		marshaller.marshal(rootElement, file);
	}

	public static void writeXMLOutputStream(Class clazz, Object rootElement, OutputStream stream) throws JAXBException {
		Marshaller marshaller = prepareMarshaller(clazz);
		marshaller.marshal(rootElement, stream);

	}

	public static void writeXMLStringWriter(Class clazz, Object rootElement, StringWriter writer) throws JAXBException {
		Marshaller marshaller = prepareMarshaller(clazz);
		marshaller.marshal(rootElement, writer);
	}
}
