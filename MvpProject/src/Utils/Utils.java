package Utils;

public class Utils {
	
	static public final String MatrixToString(int [][] matrix, int x , int y){
		StringBuilder sb = new StringBuilder();
		int j,k = 0;				
		for( j = 0 ; j< x; j++){
			for( k = 0; k <y; k++){
				sb.append(matrix[j][k]);
			}
			sb.append("\n");
		}
		return(sb.toString());
	}
}

