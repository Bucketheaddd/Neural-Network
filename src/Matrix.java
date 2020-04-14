
public class Matrix {
	
	private double[][] m;
	private int rows;
	private int cols;
	
	public int getRows(){ return rows;}
	public int getCols(){ return cols;}
	public double[] getColArray(){ return m[0];}
	
	
	
	public Matrix copy(){
		return new Matrix(this.m);
	}
	
	/**
	 * Adds the value of n element-wise to the current matrix.
	 * @param n
	 */
	public void add(double n){
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows; j++){
				m[i][j] += n;
			}
		}
	}	
	
	/**
	 * Add matrices element-wise.
	 * @param m_ Must have same dimensions as this.
	 */
	public void add(Matrix m_){
		if(m_.getRows() == this.rows && m_.getCols() == this.cols){
			for(int i = 0; i < cols; i++){
				for(int j = 0; j < rows; j++){
					m[i][j] += m_.m[i][j];
				}
			}
		}else{
			System.out.printf("Add Mismatch: %d %d %d %d \n" , m_.getRows(), this.rows, m_.getCols(), this.cols);
		}
	}
	
	/**
	 * Applies scalar product.
	 * @param n
	 */
	public void scalar(double n){
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows; j++){
				m[i][j] *= n;
			}
		}
	}
	/**
	 * The abstract class function contains a single method, f: R -> R.
	 * Applies the implementation of f to every element of the current matrix.
	 * @param f
	 * 
	 * This is most useful to apply activation functions in a neural network implementation, usually 
	 * a sigmoid or arctan function.
	 */
	public void apply(Function f){
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows; j++){
				m[i][j] = f.f(m[i][j]);
			}
		}
	}
	
	/**
	 * Assigns ranged random values to each element in current matrix.
	 * @param min
	 * @param max
	 */
	public void random(double min, double max){
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows; j++){
				m[i][j] = (Math.random() * (max - min)) + min; 
			}
		}
	}
	/**
	 * Adds ranged random values to each element in current matrix.
	 * @param min
	 * @param max
	 */
	public void addrandom(double min, double max){
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows; j++){
				m[i][j] += (Math.random() * (max - min)) + min; 
			}
		}
	}
	
	/**
	 * Pretty print matrix to see what we are doing
	 */
	public void print(){
		for(int j = rows - 1; j >= 0; j--){
			
			String line = "|", divide = "";
			
			for(int i = 0; i < cols; i++){
				line += m[i][j] + "|";
			}
			System.out.println(line);
		}
		System.out.println();
	}
	
	/**
	 * Performs Matrix multiplication between this matrix and m_. 
	 * Returns null if the operation cannot be performed
	 * @param m_
	 * @return
	 */	
	public Matrix matrixproduct(Matrix m_){
		
		Matrix m_new = null;
		if(this.getCols() == m_.getRows()){
			int y = this.getRows(), x = m_.getCols(), n = this.getCols();
			m_new = new Matrix(x,y);
			for(int i = 0; i < x; i++){
				for(int j = 0; j < y; j++){
					for(int k = 0; k < n; k++){
						m_new.m[i][j] += m[k][j] * m_.m[i][k];
					}
				}
			}
			 
		}else{
			System.out.printf("Matrix product mismatch: %d, %d\n", this.getCols(), m_.getRows());
			return m_new;
		}
		
		return m_new;			
	}
	/**
	 * Transposes current matrix, returns result as new matrix
	 * @return Current matrix with rows and columns swapped
	 */
	public Matrix transpose(){
		Matrix m_new = new Matrix(this.getRows(), this.getCols());
		
		for(int x = 0; x < this.getCols(); x++){
			for(int y = 0; y < this.getRows(); y++){
				m_new.m[y][x] = m[x][y]; 
			}
		}
		
		return m_new;
	}

	public Matrix(double[][] m_){

		this.cols = m_.length;
		this.rows = m_[0].length;
		this.m = new double[this.cols][this.rows];

		for(int i = 0; i < cols; i++){

			for(int j = 0; j < rows; j++){
				m[i][j] = m_[i][j];
			}
		}
	}	

	public Matrix(int cols, int rows){
		m = new double[cols][rows];
		this.rows = rows;
		this.cols = cols;
		
		for(int i = 0; i < cols; i++){
			
			for(int j = 0; j < rows; j++){
				m[i][j] = 0;
			}
		}
	}
	
	public Matrix(double[] columnVector){
		m = new double[1][columnVector.length];
		
		this.rows = columnVector.length;
		this.cols = 1;
		
		for(int i = 0; i < columnVector.length; i++){
			m[0][i] = columnVector[i];
		}
	}
}
