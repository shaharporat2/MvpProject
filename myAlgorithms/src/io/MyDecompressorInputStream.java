package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	
	private InputStream in;
	
	
	public MyDecompressorInputStream(InputStream in) {
		super();
		this.in = in;
	}

	@Override
	public int read() throws IOException {
		return in.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		
		int iter = 0 ;
		int nOfByte = 0;
		int curByte = 0;
				
		do{
			curByte = in.read();
			nOfByte = in.read();
			if(curByte == -1){
				break;
			}
			for(int i=0; i < nOfByte; i++){
					b[iter] = (byte)curByte;
					iter++;
			}
		}while(curByte != -1);
		
		return super.read(b);
	}
	
	
}
