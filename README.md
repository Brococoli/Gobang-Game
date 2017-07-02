五子棋游戏-Java
===============

这学期期末Java实训，我选了个五子棋游戏，第一次使用写GUI，感觉很有趣，写了许多功能，当然也有很多问题</br>



类的设计
--------
- GUI.game
	- Chessboard 类：五子棋的底层算法
	- 	初始化棋盘
	- 	下一子（黑或白）
	- 	移去某一字（悔棋）
	- 	AI下一子
	- 	判断是否有人赢了


- GUI.StartGame
这个类是游戏启动的入口，包含main函数
	- 程序入口


- GUI.Interface
这个类是用户主界面，继承自JFrame
其相关操作有：
	- 设计菜单栏及其功能
	- 设置棋盘大小
	- 设置对战模式
	- 设置皮肤
	- 设计皮肤
	- 给其他类提供底层界面

- GUI.ChessboardUI
这个类是棋盘和棋子界面设计，继承自JPanel
其相关操作有：
	- 绘制棋盘
	- 绘制棋子
	- 监听鼠标点击事件

- GUI.ChessboardDialog
这个类是修改棋盘大小尺寸的对话框，继承自JDialog
	- 修改棋盘大小

- GUI.resultDialog
这个类是显示比赛结果的对话框，继承自JDialog
其相关操作有：
	- 显示谁赢了比赛
	- “再来一局”按钮可重新开局


电脑走棋思路
-----------
思路借鉴 [**五子棋AI图形界面人机对战(JAVA实现)**](http://www.2cto.com/kf/201603/494106.html)十分感谢！
算法核心 ：***最大最小搜索 +  Alpha-Beta剪枝***

简单地说当电脑落子的时候，假设放置在某个位置，接下来再假设玩家放置在某个位置，重复此操作，直到到达某个深度的时候比较双方的估值函数，若电脑的估值函数大则电脑赢得几率大。通过枚举电脑写的每个点，就可以得到棋盘中每个点的电脑的估值函数与玩家的估值函数的差值，其中差值最大的地方就是对电脑最有利的地方。

**估值函数设计:**
- 判断是否能成5, 如果是机器方的话给予100000分，如果是人方的话给予-100000 分；
- 判断是否能成活4或者是双死4或者是死4活3，如果是机器方的话给予10000分，如果是人方的话给予-10000分；
- 判断是否已成双活3，如果是机器方的话给予5000分，如果是人方的话给予-5000 分；
- 判断是否成死3活3，如果是机器方的话给予1000分，如果是人方的话给予-1000 分；
- 判断是否能成死4，如果是机器方的话给予500分，如果是人方的话给予-500分；
- 判断是否能成单活3，如果是机器方的话给予200分，如果是人方的话给予-200分；
- 判断是否已成双活2，如果是机器方的话给予100分，如果是人方的话给予-100分；
- 判断是否能成死3，如果是机器方的话给予50分，如果是人方的话给予-50分；
- 判断是否能成双活2，如果是机器方的话给予10分，如果是人方的话给予-10分；
- 判断是否能成活2，如果是机器方的话给予5分，如果是人方的话给予-5分；
- 判断是否能成死2，如果是机器方的话给予3分，如果是人方的话给予-3分。


换肤设计
----------
使用的是外部jar包：substance.jar
所以在运行源文件时，不要忘记**引入外部包substance.jar**


运行
--------------
clone后双击"五子棋游戏.jar"
或者import到eclipse/myeclipse里，引入外部包substance.jar（在lib文件夹里），在点击运行


其他
----------
第一次写GUI，Java项目，面向对象思想，可能有许多错误，请多多指教





