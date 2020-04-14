
public class Network {
	
	Matrix[] biases;
	Matrix[] weights;
	Function act;
	
	
	public void print(){
		for(Matrix m : weights){
			m.print();
		}
	}
	
	public Network copy(){
		return new Network(weights, biases, act);
	}
	
	public void random(double min, double max){
		for(int i = 0; i < weights.length; i++){
			biases[i].random(min, max);
			weights[i].random(min, max);
		}
	}
	
	public void addrandom(double min, double max){
		for(int i = 0; i < weights.length; i++){
			biases[i].addrandom(min, max);
			weights[i].addrandom(min, max);
		}
	}
	
	
	
	//Feed forward algorithm
	public Matrix guess(Matrix input){
		Matrix in = input;
		
		for(int i = 0; i < weights.length; i++){
			in = weights[i].matrixproduct(in);
			in.add(biases[i]);
			in.apply(act);
		}
		
		return in;
		
	} 
	
	public Network(Matrix[] weights, Matrix[] biases, Function f){
		this.act = f;
		this.weights = new Matrix[weights.length];
		this.biases = new Matrix[weights.length];
		
		for(int i = 0; i < weights.length; i++){
			this.weights[i] = weights[i].copy();
			this.biases[i] = biases[i].copy();
		}
	}
	
	public Network(int[] nodes, Function f){
		act = f;
		
		weights = new Matrix[nodes.length - 1];
		biases = new Matrix[nodes.length - 1];
		for(int i = 0; i < nodes.length - 1; i++){
			weights[i] = new Matrix(nodes[i], nodes[i+1]);
			weights[i].random(-1, 1);
			biases[i] = new Matrix(1, nodes[i+ 1]);
			biases[i].random(-1, 1);
		}		
	}
}


