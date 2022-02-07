package word_test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MyFrame extends JFrame implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int word_num = 3;
	
	//영-한 or 한-영 pair 
	String[][] word = new String[1000][2];
	
	JPanel panel = new JPanel();
	//JScrollPane pane = new JScrollPane(panel);
	JPanel panel_label = new JPanel();
	JPanel panel_start = new JPanel();
	
	JButton saveButton[] = new JButton[15];
	JButton startButton = new JButton("시험시작"); 
	JButton submitButton = new JButton("답안제출");
	
	JLabel label = new JLabel("** 영-한 또는 한-영 뜻을 입력 후 각각 save 해주세요..");
	JLabel label2 = new JLabel("모든 단어의 save가 끝나고 시험 시작 버튼이 나타납니다. **");
	
	JTextField[] word1_input = new JTextField[16];
	JTextField word2_input[] = new JTextField[16];
			
	
	public MyFrame() 
	{
		
		super("단어 시험기");

		label.setFont(new Font(null, Font.BOLD, 13));
		label2.setFont(new Font(null, Font.BOLD, 13));
		
		submitButton.addActionListener(this);
		submitButton.setPreferredSize(new Dimension(300,100));
		submitButton.setFont(new Font(null, Font.BOLD, 20));
		
		startButton.addActionListener(this);
		startButton.setPreferredSize(new Dimension(300,100));
		startButton.setFont(new Font(null, Font.BOLD, 20));
		startButton.setVisible(false);
		
		
		panel.setBounds(100,100,700,700);
		
		panel_label.add(label); panel_label.add(label2);
		panel_start.add(startButton);
		
		
		//단어입력칸 word_num 만큼 생성
		for(int i=0;i<word_num;i++)
		{
					
				word1_input[i] = new JTextField(20);
				word2_input[i] = new JTextField(20);
				saveButton[i] = new JButton((i+1)+"번 save");
				saveButton[i].addActionListener(this);
				
				panel.add(word1_input[i]);
				panel.add(word2_input[i]); 
				panel.add(saveButton[i]);
				
				word1_input[i].setBounds(100,50*i,300,30);
				word2_input[i].setBounds(500,50*i,300,30);
				saveButton[i].setBounds(810,50*i,100,30);
				
		}
		
		
		add(panel_label,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
		add(panel_start,BorderLayout.SOUTH);
		
		
		setBounds(100,100,700,700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
		
	}
	
	
	public void shuffleWord(){
		
		
		for(int i=0;i<word_num;i++)
		{
		
		String[] tmp_1 = new String[2];
		
		Random rand = new Random();
		int random_index1 = rand.nextInt(word_num);
		int random_index2 = rand.nextInt(word_num);
		
		
		tmp_1[0] = word[random_index1][0];
		tmp_1[1] = word[random_index1][1];
		
		word[random_index1][0] = word[random_index2][0];
		word[random_index1][1] = word[random_index2][1];

		word[random_index2][0] = tmp_1[0];
		word[random_index2][1] = tmp_1[1];
		
		}//end of for 
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().contains("save"))  //submit 버튼 
		{
			
			String input = new String(); input = e.getActionCommand();
		
			String[] index = input.split("번");
			int index_i =Integer.parseInt(index[0])-1;
		
			//확인용
			//System.out.println(index_i);
			
			// 한, 영 모두 채워야 
			if( word1_input[index_i].getText().isBlank() || word2_input[index_i].getText().isBlank() )
			{
				JOptionPane.showMessageDialog(null, "칸을 채워주세요","warning!", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			//순차적으로 채워야 함
			if( index_i > 1 && (word1_input[index_i-1].getText().isBlank() && word2_input[index_i-1].getText().isBlank()) )
			{
				JOptionPane.showMessageDialog(null, "앞의 칸을 먼저 채우세요","warning!", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			word[index_i][0] = word1_input[index_i].getText(); 
			word[index_i][1] = word2_input[index_i].getText(); 
		
			//확인용
			System.out.println("확인용");
			System.out.println(word[index_i][0]+" "+word[index_i][1]);
			
			
			word1_input[index_i].setVisible(false); 
			word2_input[index_i].setVisible(false);
			saveButton[index_i].setVisible(false);
			
			//start 버튼 생성
			if(index_i == word_num-1)
				startButton.setVisible(true);
		
		}//end of if save
		
		if(e.getSource().equals(startButton)) //start 버튼 
		{
			shuffleWord();
			
			startButton.setVisible(false);
			label2.setVisible(false);
			
			label.setText("빈 칸에 알맞은 뜻을 입력 후 전체 답안을 제출해 주세요");;
			
			//확인용
			for(int i=0;i<word_num;i++)
				System.out.println(word[i][0]+" "+word[i][1]);
			
			
			
			for(int i=0;i<word_num;i++)
			{
				
				panel.add(word1_input[i]);
				panel.add(word2_input[i]); 
				panel.add(saveButton[i]);

				
				word1_input[i].setText(word[i][0]);
				word2_input[i].setText("");
				
				word1_input[i].setBounds(100,50*i,300,30);
				word2_input[i].setBounds(500,50*i,300,30);
				
				word1_input[i].setVisible(true); 
				word2_input[i].setVisible(true);
	
				
			}
			
			panel_start.add(submitButton);
			
			
		}//end of if start
		
		if(e.getSource().equals(submitButton))
		{
			//확인용
			System.out.println("submit check!");
			
			submitButton.setVisible(false);
			
			double wrong_answer=0;
			double sum = (double)word_num;
			double per=0;
			
			for(int i=0;i<word_num;i++)
			{
				//틀린 답안인 경우 
				if(!word2_input[i].getText().contains(word[i][1]))
				{
					wrong_answer++; 
					
					word2_input[i].setBackground(Color.pink);
					
				}
				
			}
			
			per=( (word_num-wrong_answer)/sum )*100;
			
			
			JLabel result = new JLabel("정답비율: "+ per+"%");
			JLabel result2 = new JLabel(" *같은 답이 아니라도 정답을 포함하면 정답으로 채점됩니다.");
		
			result.setFont(new Font(null, Font.BOLD, 20));
			result2.setFont(new Font(null, Font.BOLD, 10));
			
			panel_start.add(result);
			panel_start.add(result2);
			
		}
		
		
	}//end of action_listener 
	
	


}


