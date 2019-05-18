/*
 * ComputeExpression.java
 *
 * Created on 2. November 2001, 10:26
 */
package net.msk.csvedit;

import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Es wird eine einfache Formel ausgewertet, als Operatoren sind nur +,*,- und /
 * zugelassen. Die Operanden sind entweder feste Zahlen oder <strong>x</strong>
 * f&uuml;r den aktuellen Wert, beim Durchlaufen der Spalte.
 *
 * @author Koehler
 */
public class ComputeExpression
{

    private Properties var = new Properties();
    private String expression;

    /**
     * Creates new ComputeExpression
     */
    public ComputeExpression()
    {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        ComputeExpression ce = new ComputeExpression();

        ce.setExpression("--x+-x");
        ce.putVariable("x", new Double(42));
        System.out.println(ce.computeExpr().toString());
    }

    /**
     * parse the expression String and compute the result, the expression
     * is evaluated from left to rigth without precedence. The only operators
     * are *,/,- and + and the operands double values or strings with an value
     * in var.
     */
    public Double computeExpr()
    {
        char op;
        boolean neg = false;
        double result = 0.0;
        double val;
        StringTokenizer expr = new StringTokenizer(expression, "*/-+", true);

        op = 'f';
        while (expr.hasMoreTokens())
        {
            String tok = expr.nextToken();
            System.out.print("Token: " + tok);
            if (tok.charAt(0) == '+' || tok.charAt(0) == '-'
                || tok.charAt(0) == '*' || tok.charAt(0) == '/')
            {
                // we have found an operator
                System.out.print(" --> Operator.");
                if (op != 'u' && tok.charAt(0) == '-') neg = !neg;
                if (op == 'u') op = tok.charAt(0);
                System.out.println(String.valueOf(op) + ":" + String.valueOf(neg));
                // next token
                continue;
            } else if (var.get(tok) != null)
            {
                // we have found an variable
                System.out.print(" --> Variable: ");
                val = ((Double) var.get(tok)).doubleValue();
                System.out.println(val);
            } else
            {
                // an constant number
                System.out.print(" --> Number: ");
                try
                {
                    val = Double.parseDouble(tok);
                } catch (NumberFormatException e)
                {
                    System.err.println(e.getMessage());
                    val = 0.0;
                }
                System.out.println(val);
            }
            // now compute the new result
            if (neg) val *= -1;
            if (op == 'f')
            {
                result = val;
            } else
            {
                switch (op)
                {
                    case '+':
                        result += val;
                        break;
                    case '-':
                        result -= val;
                        break;
                    case '*':
                        result *= val;
                        break;
                    case '/':
                        result /= val;
                        break;
                }
            }
            // set the operator to an unknown value
            op = 'u';
            System.out.println("Result: " + String.valueOf(result));
        }
        return new Double(result);
    }

    /**
     * Insert a new property (variable)
     */
    public void putVariable(String name, Double value)
    {
        var.put(name, value);
    }

    /**
     * set the expression for later computing add an semicolon
     */
    public void setExpression(String e)
    {
        expression = new String(e);
    }

}
