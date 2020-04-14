import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		
		Function f = new Function(){
			public double f(double x){
				return Math.tanh(x);
			}
		};
		
		int[] input = new int[]{42,42,7};
		Matrix guess = new Matrix(new double[42]);

		Network n = new Network(input, f);
		Network nn = n.copy();
		
		nn.random(0, 0);
		n.guess(guess).print(); 
		System.out.println(Arrays.toString(n.guess(guess).getColArray()));
		nn.guess(guess).print();
		
		
		
		//n.print();
		
	}
}

