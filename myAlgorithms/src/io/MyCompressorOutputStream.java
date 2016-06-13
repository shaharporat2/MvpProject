package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	
	/*
	 * (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	
	/*Data Mambers*/
	
	private OutputStream out;
	
	
	
	/**
	 * 
	 * @param out
	 */
	public MyCompressorOutputStream(OutputStream out) {
		super();
		this.out = out;
	}

	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	
	@Override
	public void write(byte [] b) throws IOException {
			
		byte lastbyte = b[0];
		
		int count = 1;
		
		for(int i = 1; i < b.length ; i++){
			if(b[i] == lastbyte){
				count++;
			}
			else{
				while(count > 255){
					out.write(lastbyte);
					out.write(255);
					count = count - 255;
				}
				out.write(lastbyte);
				out.write(count);
				
				lastbyte = b[i];
				count = 1;
			}
		}
		
		while(count > 255){
			out.write(lastbyte);
			out.write(255);
			count = count - 255;
		}
		out.write(lastbyte);
		out.write(count);
	}
}
