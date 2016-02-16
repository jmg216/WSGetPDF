package com.isa.ws;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.isa.entities.Documento;
import com.isa.exception.WsException;
import com.isa.utiles.UtilesResources;

public class WSFirmaDoc {

	public static String TIPO_PDF = "pades";
	public static String TIPO_XML = "xades";
	public static String EXTENSION_XML = ".xml";
	public static String EXTENSION_PDF = ".pdf";
	
	
	public Documento obtenerDocumentoParaFirmar( String[] params ) throws WsException{
		System.out.println("WSFirmaDoc::obtenerDocumentoParaFirmar");
		try {
			String nombre = params[1];
			String path = UtilesResources.getProperty("WSConfig.origen");
			Documento documento = null;
			
			if (params[0].equals(TIPO_XML)){
				Documento xml = new Documento();
				xml.setId(1);
				xml.setTipo(TIPO_XML);
				xml.setExt(EXTENSION_XML);
				Path filename = Paths.get( path + nombre );
				byte[] data = Files.readAllBytes(filename);	
				xml.setDocumento( data );
				
				documento = xml;				
			}
			
			if (params[0].equals(TIPO_PDF)){
				
				Documento pdf = new Documento();
				pdf.setId(1);
				pdf.setTipo(TIPO_PDF);
				pdf.setExt(EXTENSION_PDF);
				Path filename = Paths.get( path + nombre );
				byte[] data = Files.readAllBytes(filename);	
				pdf.setDocumento( data );
				
				documento = pdf;				
			}
			return documento;
		} 
		catch (IOException e) {
			throw new WsException("Error accediendo a archivo de configuración", e.getCause());
		}		
	}
	
	
	public Documento obtenerDocumentoParaValidar( String[] params ) throws WsException{
		System.out.println("WSFirmaDoc::obtenerDocumentoParaValidar");
		try {
			String nombre = params[1];
			String path = UtilesResources.getProperty("WSConfig.validar");
			
			Documento documento = null;
			
			if (params[0].equals(TIPO_XML)){
				Documento xml = new Documento();
				xml.setId(1);
				xml.setExt(EXTENSION_XML);
				Path filename = Paths.get( path + nombre );
				byte[] data = Files.readAllBytes(filename);	
				xml.setDocumento( data );
				
				documento = xml;				
			}
			
			if (params[0].equals(TIPO_PDF)){
				
				Documento pdf = new Documento();
				pdf.setId(1);
				pdf.setExt(EXTENSION_PDF);
				Path filename = Paths.get( path + nombre );
				byte[] data = Files.readAllBytes(filename);	
				pdf.setDocumento( data );
				
				documento = pdf;				
			}
			return documento;
			
		} 
		catch (IOException e) {
			throw new WsException("Error accediendo a archivo de configuración", e.getCause());
		}		
	}	
	
	
	public int guardarDocumento( Documento documento ) throws WsException{
		System.out.println("WSFirmaDoc::guardarDocumento");
		OutputStream os = null;
		try{			
			SimpleDateFormat FULL_FORMAT_DATA = new SimpleDateFormat("yyyymmddhhmmss");
			
			String path = UtilesResources.getProperty("WSConfig.destino");
			String filename = path  + "Firmado_" + FULL_FORMAT_DATA.format(new Date()) + documento.getExt();
			
			os = new FileOutputStream( filename );
			os.write( documento.getDocumento() );
			os.close();
			
			return 1;
		}
		catch( IOException e ){
			e.printStackTrace();
			throw new WsException("Error accediendo a archivo de configuración", e.getCause());
		}
		 finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
	}
}
