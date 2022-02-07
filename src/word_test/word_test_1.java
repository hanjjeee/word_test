package word_test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class word_test_1 extends JFrame implements ActionListener{
	
	JLabel label;
	
	JPanel label_panel;
	JPanel cnt_panel;
	JPanel button_panel;
	
	JTextField count_text;
	JButton cntButton;
	
	public word_test_1() {
		
		super("단어시험");
		
		label = new JLabel("** 시험 볼 단어 개수 (1개 - 15개)**");
		
		label_panel = new JPanel();
		cnt_panel = new JPanel();
		button_panel = new JPanel();
		
		count_text = new JTextField(10);
		cntButton = new JButton("개수 입력");
		cntButton.addActionListener(this);
		
		label_panel.add(label);
		cnt_panel.add(count_text);
		button_panel.add(cntButton);
		
		add(label_panel, BorderLayout.NORTH);
		add(cnt_panel, BorderLayout.CENTER);
		add(button_panel, BorderLayout.SOUTH);
		
		setBounds(100,100,200,200);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args)
	{
		new word_test_1();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cntButton)
		{
			
			if(Integer.parseInt( count_text.getText() ) <= 0 || Integer.parseInt( count_text.getText() ) > 15 )
			{
				JOptionPane.showMessageDialog(null,"1개~15개 까지 가능합니다.","앙 error 띠~!",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			MyFrame.word_num = Integer.parseInt( count_text.getText() );
			new MyFrame();
			setVisible(false);
		}
	}

}
