package calculadora;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tokenizer {
    private List<String> values;
    private Node node;
    public void set_node(Node _node){
        node = _node;
    }
    public void set_expression(String expression){
        int first, second;
        values = new ArrayList<String>();
        for(char i : expression.toCharArray()){
            if(i != ' '){
                values.add(Character.toString(i));
            }
        }
        first = 0;
        second = 0;
        while(!values.get(second).equals(")")){
            second = second + 1;
            if(second >= values.size()){
                break;
            }
        }
        if(values.size() <= second){
            first = 0;
            second = 0;
            if(values.get(second).equals("-") || values.get(second).equals("+")){
                second += 1;
            }
            while(!values.get(second).equals("+") && !values.get(second).equals("-")){
                second += 1;
                if(second >= values.size()){
                    break;
                }
            }
            if(values.size() <= second){
                second = 0;
                while(!values.get(second).equals("*") && !values.get(second).equals("/")){
                    second += 1;
                    if(second >= values.size()){
                        break;
                    }
                }
                if(values.size() <= second){
                    node.value = expression;
                } else {
                    node.left = new Node();
                    node.left.set_expression(get_range(first, second));
                    node.value = values.get(second);
                    node.right = new Node();
                    node.right.set_expression(get_range(second+1, values.size()));
                }
            } else {
                node.left = new Node();
                node.left.set_expression(get_range(first, second));
                node.value = values.get(second);
                node.right = new Node();
                node.right.set_expression(get_range(second+1, values.size()));
            }
        } else {
            Stack<Integer> parenthesis = new Stack<>();
            first = 0;
            second = 0;
            if(values.get(second).equals("-") || values.get(second).equals("+")){
                second += 1;
            }
            while(true){
                if(values.get(second).equals("(")){
                    parenthesis.push(1);
                }
                if(values.get(second).equals(")")){
                    parenthesis.pop();
                }
                if((values.get(second).equals("+") || values.get(second).equals("-")) && parenthesis.empty()){
                    break;
                }
                second += 1;
                if(second >= values.size()){
                    break;
                }
            }
            if(values.size() <= second){
                second = 0;
                while(true){
                    if(values.get(second).equals("(")){
                        parenthesis.push(1);
                    }
                    if(values.get(second).equals(")")){
                        parenthesis.pop();
                    }
                    if((values.get(second).equals("*") || values.get(second).equals("/")) && parenthesis.empty()){
                        break;
                    }
                    second += 1;
                    if(second >= values.size()){
                        break;
                    }
                }
                if(values.size() <= second){
                    if(values.get(0).equals("(")){
                        node.set_expression(get_range(1, values.size()-1));
                    } else {
                        node.value = expression;
                    }
                } else {
                    node.left = new Node();
                    node.left.set_expression(get_range(first, second));
                    node.value = values.get(second);
                    node.right = new Node();
                    node.right.set_expression(get_range(second+1, values.size()));
                }
            } else {
                node.left = new Node();
                node.left.set_expression(get_range(first, second));
                node.value = values.get(second);
                node.right = new Node();
                node.right.set_expression(get_range(second+1, values.size()));
            }
        }
    }
    public String get_range(int start, int end){
        String range = "";
        for(int i = start; i < end; i++){
            range += values.get(i);
        }
        return range;
    }
}
