package calculadora;

public class Node {
    public Node left;
    public Node right;
    public String value;
    public void set_expression(String _expression){
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.set_node(this);
        tokenizer.set_expression(_expression);
    }
    public float get_value(){
        return Float.parseFloat(value);
    }
    public void operate(){
        if(left != null) {
            switch (left.value) {
                case "-":
                    left.operate();
                    break;
                case "+":
                    left.operate();
                    break;
                case "*":
                    left.operate();
                    break;
                case "/":
                    left.operate();
                    break;
            }
        }
        if(right != null) {
            switch (right.value) {
                case "-":
                    right.operate();
                    break;
                case "+":
                    right.operate();
                    break;
                case "*":
                    right.operate();
                    break;
                case "/":
                    right.operate();
                    break;
            }
        }
        if(this.value.equals("-")){
            value = Float.toString(Float.parseFloat(left.value) - Float.parseFloat(right.value));
        } else if(this.value.equals("+")){
            value = Float.toString(Float.parseFloat(left.value) + Float.parseFloat(right.value));
        } else if(this.value.equals("*")){
            value = Float.toString(Float.parseFloat(left.value) * Float.parseFloat(right.value));
        } else if(this.value.equals("/")){
            value = Float.toString(Float.parseFloat(left.value) / Float.parseFloat(right.value));
        }
    }
}
