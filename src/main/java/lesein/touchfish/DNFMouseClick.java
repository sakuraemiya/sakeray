package lesein.touchfish;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;

/**
 * @author WangJie
 * @date 2022/7/18
 */
public class DNFMouseClick {
    public static void main(String[] args) throws InterruptedException, AWTException {
        System.out.println("1:左键连点            2:右键连点            3:获取坐标");
        System.out.println("4:DNF刷新任务模式      5:DNF未央洗词条模型    6:退出");
        while (true){
            System.out.print("请选择你想实现的类型：");
            Scanner scanner=new Scanner(System.in);
            int operate=scanner.nextInt();
            switch (operate){
                case 1:
                    System.out.println("未实现");
                    break;
                case 2:
                    System.out.println("未实现");
                    break;
                case 3:
                    getPoint();
                    break;
                case 4:
                    DNFRefreshTaskModel();
                    break;
                case 5:
                    System.out.println("未实现");
                    break;
                case 6:
                    System.exit(0);
                    return;
                default:
                    System.out.println("瞎输什么");
            }
        }
    }
    public static void getPoint() throws InterruptedException {
        System.out.println("你有3秒钟的时间将鼠标放到要获取坐标的位置");
        Thread.sleep(3000);
        Point point = MouseInfo.getPointerInfo().getLocation();
        System.out.println("x="+point.getX()+","+"y="+point.getY());
    }
    public static void DNFRefreshTaskModel() throws InterruptedException, AWTException {
        double startX,startY,endX,endY,num;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入起始坐标点X坐标：");
        startX = Double.parseDouble(scanner.next());
        System.out.print("请输入起始坐标点Y坐标：");
        startY = Double.parseDouble(scanner.next());
        System.out.print("请输入结束坐标点X坐标：");
        endX = Double.parseDouble(scanner.next());
        System.out.print("请输入结束坐标点Y坐标：");
        endY = Double.parseDouble(scanner.next());
        System.out.print("请输入要执行的次数：");
        num =  scanner.nextInt();
        System.out.println("请将鼠标置于DNF游戏内并点击一次，5秒后开始刷任务");
        Thread.sleep(5000);
        Robot robot=new Robot();
        for(int i =0 ;i<num ; i++){
            robot.mouseMove((int)startX, (int)startY);
            Thread.sleep(100);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            Thread.sleep(500);

            robot.mouseMove((int)endX, (int)endY);
            Thread.sleep(100);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            Thread.sleep(500);
        }
    }
}
