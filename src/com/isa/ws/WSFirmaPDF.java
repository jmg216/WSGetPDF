package com.isa.ws;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.isa.exception.WsException;
import com.isa.utiles.UtilesResources;

public class WSFirmaPDF {

	
	public PDF obtenerPDFParaFirmar( String nombre ) throws WsException{
		try {
			PDF pdf = new PDF();
			
			pdf.setId(1);
			String path = UtilesResources.getProperty("WSConfig.origen");
	
			Path filename = Paths.get( path + nombre );
			byte[] data = Files.readAllBytes(filename);	
			
			pdf.setDocumento( data );
			
			return pdf;
		
		} 
		catch (IOException e) {
			throw new WsException("Error accediendo a archivo de configuración", e.getCause());
		}		
	}
	
	
	public PDF obtenerPDFParaValidar( String nombre ) throws WsException{
		try {
			PDF pdf = new PDF();
			
			pdf.setId(1);
			String path = UtilesResources.getProperty("WSConfig.validar");
	
			Path filename = Paths.get( path + nombre );
			byte[] data = Files.readAllBytes(filename);	
			
			pdf.setDocumento( data );
			
			return pdf;
		
		} 
		catch (IOException e) {
			throw new WsException("Error accediendo a archivo de configuración", e.getCause());
		}		
	}	
	
	
	public int guardarPDF( PDF pdf ) throws WsException{
		OutputStream os = null;
		try{
			SimpleDateFormat FULL_FORMAT_DATA = new SimpleDateFormat("yyyymmddhhmmss");
			
			String path = UtilesResources.getProperty("WSConfig.destino");
			String filename = path  + "Firmado_" + FULL_FORMAT_DATA.format(new Date()) + ".pdf";
			
			os = new FileOutputStream( filename );
			os.write( pdf.getDocumento() );
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
