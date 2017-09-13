package com.wf.adc;

import java.nio.channels.ShutdownChannelGroupException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * 后宫选妃java版
 * @author 宇
 *
 */
public class AppMain {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//野生美女的姓名数组
		String[] newNameArray = {"褒姒","陈圆圆","苏小小","甄姬","钟无艳"};
		int newNameCount = newNameArray.length;//总数
		String[] nnNameArray = {"西施","貂蝉","王昭君","杨玉环","赵飞燕","","","","","","",};//后宫娘娘名称
		String[] levelNames = {"贵人","嫔妃","贵妃","皇贵妃","皇后"};
		int[] level = new int[10];//对应每个娘娘的级别
		int[] loves = new int[10];//对应每个娘i的好感度
		
		
		int nncount = 5;//娘娘的初始数量为5
		int gameDays = 1;//游戏默认时间为10
		//设定默认好感度
		for(int i= 0;i< nncount;i++){
			loves[i] = 100;
		}
		JOptionPane.showMessageDialog(null, "陛下，你来了", "欢迎来到后宫选妃", 1, new ImageIcon("images/title.jpg"));
		while(gameDays <= 10){
//			System.out.println("游戏进行到第" + gameDays + "天");
//			System.out.println("1、皇上下旨选妃\t\t(增加)");
//			System.out.println("2、翻牌宠幸\t\t(修改状态)");
//			System.out.println("3、打入冷宫\t\t(删除)");
//			System.out.println("4、朕的爱妃呢？\t\t(查找修改状态)");
//			System.out.println("陛下请选择：");
			String strMenu = "1、皇上下旨选妃\n";
			strMenu += "2、翻牌宠幸\n";
			strMenu += "3、打入冷宫\n";
			strMenu += "4、朕的爱妃呢？\n";
			strMenu += "陛下请选择";
			Object objResult = JOptionPane.showInputDialog(null, strMenu, "游戏进行第" + gameDays + "天" , 0, new ImageIcon("images/宝座.jpg"), new String[]{"1","2","3","4"}, 4);
			//int choice = input.nextInt();
			int choice = Integer.parseInt(objResult.toString());
			switch (objResult.toString()) {
			case "1"://1、皇上下旨选妃\t\t(增加)
				if(nncount == nnNameArray.length){
					System.out.println("皇帝陛下，后宫已经人满为患了，添加失败");
					break;
				}
//				System.out.println("请输入娘娘的名讳：");
//				String newName = input.next();
				 objResult = JOptionPane.showInputDialog(null, "请选择秀女", "选妃",0,new ImageIcon("images/选妃.jpg"),newNameArray,null);
				if(objResult == null){
					continue;
				}
				JOptionPane.showMessageDialog(null, objResult.toString(), "选择的嫔妃", 0, new ImageIcon("images/" + objResult.toString() + ".jpg"));
				//增加
				nnNameArray[nncount] = objResult.toString();
				loves[nncount] = 100;
				
				//其他娘娘的好感度-10
				for(int i = 0;i < nncount;i++){
					loves[i] -= 10;
				}
				nncount++;
				break;
			case "2"://2、翻牌宠幸\t\t(修改状态)
//				System.out.println("请输入娘娘名讳：");
//				String name = input.next();
				 objResult = JOptionPane.showInputDialog(null, "陛下，请选择", "翻牌", 0,
						new ImageIcon("images/选妃翻牌.jpg"),nnNameArray,null);
				 if(objResult == null){
					 continue;
				 }
				 String name = objResult.toString();
				//查找
				int searchIndex = -1;
				for (int i = 0; i < nncount; i++) {
					if(name.compareTo(nnNameArray[i]) == 0){//性名相等
						searchIndex = i;
						//break;
					}else{
						
					}
				}
				if(searchIndex == Integer.MIN_VALUE){
					System.out.println("没找到");
					break;
				}
				//找到的话。当前好感度加20
				loves[searchIndex] += 20;
				if(level[searchIndex] + 1 == levelNames.length){
					System.out.println(nnNameArray[searchIndex] + "娘娘已经母仪天下");
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
				//System.out.println("宠幸" + nnNameArray[searchIndex] + "，好感度+10，升级为" + levelNames[level[searchIndex]] + "其他娘娘好感度-10");
				JOptionPane.showMessageDialog(null, "宠幸" + nnNameArray[searchIndex] + "，好感度+10，升级为" + levelNames[level[searchIndex]] + "其他娘娘好感度-10",
						"翻牌的结果",0,new ImageIcon("images/" + nnNameArray[searchIndex] + ".jpg"));
				break;
			case "3":
				break;
			case "4":
				break;
			default:
				System.out.println("必须输入1~4之间的整数字");
				continue;
			}
//			System.out.println("姓名\t级别\t好感度");
//			for(int i = 0;i<nncount;i++){
//				System.out.println(nnNameArray[i] + "\t" + levelNames[level[i]] + "\t" + loves[i]);
//			}
			//如果有3个以上好感度低于60，强制退出
			int count = 0;
			for (int i = 0;i < nncount;i++){
				if(loves[i] < 60){
					count++;
				}
			}
			if(count >= 3){
				JOptionPane.showMessageDialog(null, "后宫有三个娘娘好感度低于60，发生暴乱", "突发事件", 0, new ImageIcon("images/皇上被害.jpg"));
				System.exit(0);
			}
			
			
			//每日结算
			String value = "没羞没臊的生活又过了一天，后宫情况如下：\n";
			for (int i = 0;i < nncount;i++){
//				System.out.println(nnNameArray[i] + "\t" + levelNames[level[i]] + "\t" + loves[i]);
				value += String.format("%s  %s  %d\n",nnNameArray[i],levelNames[level[i]], loves[i]);
			}
			JOptionPane.showMessageDialog(null, value, "每日结算", 0, new ImageIcon("images/嬉戏.jpg"));
			gameDays++;
		}
		
	}
}
