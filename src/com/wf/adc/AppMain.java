package com.wf.adc;

import java.nio.channels.ShutdownChannelGroupException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * ��ѡ��java��
 * @author ��
 *
 */
public class AppMain {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//Ұ����Ů����������
		String[] newNameArray = {"���","��ԲԲ","��СС","�缧","������"};
		int newNameCount = newNameArray.length;//����
		String[] nnNameArray = {"��ʩ","����","���Ѿ�","����","�Է���","","","","","","",};//����������
		String[] levelNames = {"����","����","����","�ʹ���","�ʺ�"};
		int[] level = new int[10];//��Ӧÿ������ļ���
		int[] loves = new int[10];//��Ӧÿ����i�ĺøж�
		
		
		int nncount = 5;//����ĳ�ʼ����Ϊ5
		int gameDays = 1;//��ϷĬ��ʱ��Ϊ10
		//�趨Ĭ�Ϻøж�
		for(int i= 0;i< nncount;i++){
			loves[i] = 100;
		}
		JOptionPane.showMessageDialog(null, "���£�������", "��ӭ������ѡ��", 1, new ImageIcon("images/title.jpg"));
		while(gameDays <= 10){
//			System.out.println("��Ϸ���е���" + gameDays + "��");
//			System.out.println("1��������ּѡ��\t\t(����)");
//			System.out.println("2�����Ƴ���\t\t(�޸�״̬)");
//			System.out.println("3�������乬\t\t(ɾ��)");
//			System.out.println("4���޵İ����أ�\t\t(�����޸�״̬)");
//			System.out.println("������ѡ��");
			String strMenu = "1��������ּѡ��\n";
			strMenu += "2�����Ƴ���\n";
			strMenu += "3�������乬\n";
			strMenu += "4���޵İ����أ�\n";
			strMenu += "������ѡ��";
			Object objResult = JOptionPane.showInputDialog(null, strMenu, "��Ϸ���е�" + gameDays + "��" , 0, new ImageIcon("images/����.jpg"), new String[]{"1","2","3","4"}, 4);
			//int choice = input.nextInt();
			int choice = Integer.parseInt(objResult.toString());
			switch (objResult.toString()) {
			case "1"://1��������ּѡ��\t\t(����)
				if(nncount == nnNameArray.length){
					System.out.println("�ʵ۱��£����Ѿ�����Ϊ���ˣ����ʧ��");
					break;
				}
//				System.out.println("��������������䣺");
//				String newName = input.next();
				 objResult = JOptionPane.showInputDialog(null, "��ѡ����Ů", "ѡ��",0,new ImageIcon("images/ѡ��.jpg"),newNameArray,null);
				if(objResult == null){
					continue;
				}
				JOptionPane.showMessageDialog(null, objResult.toString(), "ѡ�������", 0, new ImageIcon("images/" + objResult.toString() + ".jpg"));
				//����
				nnNameArray[nncount] = objResult.toString();
				loves[nncount] = 100;
				
				//��������ĺøж�-10
				for(int i = 0;i < nncount;i++){
					loves[i] -= 10;
				}
				nncount++;
				break;
			case "2"://2�����Ƴ���\t\t(�޸�״̬)
//				System.out.println("�������������䣺");
//				String name = input.next();
				 objResult = JOptionPane.showInputDialog(null, "���£���ѡ��", "����", 0,
						new ImageIcon("images/ѡ������.jpg"),nnNameArray,null);
				 if(objResult == null){
					 continue;
				 }
				 String name = objResult.toString();
				//����
				int searchIndex = -1;
				for (int i = 0; i < nncount; i++) {
					if(name.compareTo(nnNameArray[i]) == 0){//�������
						searchIndex = i;
						//break;
					}else{
						
					}
				}
				if(searchIndex == Integer.MIN_VALUE){
					System.out.println("û�ҵ�");
					break;
				}
				//�ҵ��Ļ�����ǰ�øжȼ�20
				loves[searchIndex] += 20;
				if(level[searchIndex] + 1 == levelNames.length){
					System.out.println(nnNameArray[searchIndex] + "�����Ѿ�ĸ������");
					loves[searchIndex] += 10;
					break;
				}
				level[searchIndex]++;
				for (int i = 0;i < nncount;i++){
					if(i == searchIndex){
						continue;
					}
					loves[i] -= 10;
				}
				//System.out.println("����" + nnNameArray[searchIndex] + "���øж�+10������Ϊ" + levelNames[level[searchIndex]] + "��������øж�-10");
				JOptionPane.showMessageDialog(null, "����" + nnNameArray[searchIndex] + "���øж�+10������Ϊ" + levelNames[level[searchIndex]] + "��������øж�-10",
						"���ƵĽ��",0,new ImageIcon("images/" + nnNameArray[searchIndex] + ".jpg"));
				break;
			case "3":
				break;
			case "4":
				break;
			default:
				System.out.println("��������1~4֮���������");
				continue;
			}
//			System.out.println("����\t����\t�øж�");
//			for(int i = 0;i<nncount;i++){
//				System.out.println(nnNameArray[i] + "\t" + levelNames[level[i]] + "\t" + loves[i]);
//			}
			//�����3�����Ϻøжȵ���60��ǿ���˳�
			int count = 0;
			for (int i = 0;i < nncount;i++){
				if(loves[i] < 60){
					count++;
				}
			}
			if(count >= 3){
				JOptionPane.showMessageDialog(null, "������������øжȵ���60����������", "ͻ���¼�", 0, new ImageIcon("images/���ϱ���.jpg"));
				System.exit(0);
			}
			
			
			//ÿ�ս���
			String value = "û��û���������ֹ���һ�죬��������£�\n";
			for (int i = 0;i < nncount;i++){
//				System.out.println(nnNameArray[i] + "\t" + levelNames[level[i]] + "\t" + loves[i]);
				value += String.format("%s  %s  %d\n",nnNameArray[i],levelNames[level[i]], loves[i]);
			}
			JOptionPane.showMessageDialog(null, value, "ÿ�ս���", 0, new ImageIcon("images/��Ϸ.jpg"));
			gameDays++;
		}
		
	}
}
