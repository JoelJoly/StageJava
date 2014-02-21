package bank;
 
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;        

public class Main extends JFrame {
	private static final long serialVersionUID = -8198949040903550895L;
//	private JLabel label;
	private JTextField text;
	private JButton button;
	public Main() {
		Box box = Box.createVerticalBox(); 
		add(box);
		final JLabel label = new JLabel("Entrez du texte ci dessous et appuyez sur OK");
		text = new JTextField();
		button = new JButton("OK");
		box.add(label);
		box.add(text);
		box.add(button);
		box.add(new Box.Filler(new Dimension(0, 10), new Dimension(0, 200), new Dimension(0, 200)));
		setSize(400, 300);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				label.setText(text.getText());
			}
		});
	}
    public static void main(String[] args) {
    	new Main().setVisible(true);
    }
}
