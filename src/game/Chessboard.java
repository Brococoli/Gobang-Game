package game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;






public class Chessboard {
	
	private char[][] chess;   //棋盘
	private int width, height;  //宽度和高度
	private boolean withComputer = true;  //是否和电脑
	private int depth = 1;   //极大极小搜索的深度
	private char robotColor;
	private LinkedList<Integer> chessList; //保存已经下过的棋子，因为不用保存颜色，仅需要保存最后一步的棋子
	
	public void popPoint(){  //移除最后一个棋子
		int x=0, y=0;
		try{
			y = chessList.removeLast();
			x = chessList.removeLast();
			chess[x][y] = 'N';
		}catch(NoSuchElementException e){
			System.out.println("color 无法再悔棋");
		}
		
		
	}
	public int getWidth() {
		return width;
	}


	public char getChess(int x, int y) {
		return chess[x][y];
	}


	public void setChess(char[][] chess) {
		this.chess = chess;
	}
	
	public int getHeight() {
		return height;
	}
	public void putChess(int x, int y, char who) {   //放棋子
		chessList.add(x);
		chessList.add(y);
		chess[x][y] = who;
	}
	

	public void setSize(int width, int height) {  //设置尺寸
		this.width = width;
		this.height = height;
	}




	/*  判断该位置是否无子 */
    public boolean isEmpty(int x, int y) {
        return chess[x][y] == 'N';
    }
 
    
    /* 撤子 */
    public void unMove(int x, int y) {
    	popPoint();
        chess[x][y] = 'N';
    }

	public void initChess(){  //初始化棋子
		chess = new char[width+5][height+5];
		for(int i=0;i<width; i++){
			Arrays.fill(chess[i],'N');
		}
		chessList = new LinkedList<Integer>(); //初始化List
	}
	

	public String isWin(){  //判断是否有人赢，"No one" 表没有人， "Black" 表黑子赢， "White" 表白子赢
		//judge
		int whiteCount;
		int blackCount;
		for(int x=0; x<height; x++){  //检查水平方向
			whiteCount = 0;
			blackCount = 0;
			for(int y=0; y<width; y++){
				if(chess[x][y] == 'B') blackCount++;
				else blackCount = 0;
				if(chess[x][y] == 'W') whiteCount++;
				else whiteCount = 0;
				if(blackCount >= 5) return "Black";
				if(whiteCount >= 5) return "White";
			}
		}
		
		for(int y=0; y<width; y++){  //检查竖直方向
			whiteCount = 0;
			blackCount = 0;
			for(int x=0; x<height; x++){
				if(chess[x][y] == 'B') blackCount++;
				else blackCount = 0;
				if(chess[x][y] == 'W') whiteCount++;
				else whiteCount = 0;
				if(blackCount >= 5) return "Black";
				if(whiteCount >= 5) return "White";
			}
		}
		
		for(int x=0; x<height; x++){ //检查主对角线方向
			whiteCount = 0;
			blackCount = 0;
			for(int d=0; x+d<height&&d<width ; d++){
				int nx=x+d;
				int ny=0+d;
				if(chess[nx][ny] == 'B') blackCount++;
				else blackCount = 0;
				if(chess[nx][ny] == 'W') whiteCount++;
				else whiteCount=0;
				if(blackCount >= 5) return "Black";
				if(whiteCount >= 5) return "White";
			}
		}
		
		for(int y=0; y<width; y++){ //检查主对角线方向
			whiteCount = 0;
			blackCount = 0;
			for(int d=0; d<height&&y+d<width; d++){
				int nx=0+d;
				int ny=y+d;
				if(chess[nx][ny] == 'B') blackCount++;
				else blackCount = 0;
				if(chess[nx][ny] == 'W') whiteCount++;
				else whiteCount = 0;
				if(blackCount >= 5) return "Black";
				if(whiteCount >= 5) return "White";
			}
		}
		
		for(int y=0; y<width; y++){ //检查副对角线方向
			whiteCount = 0;
			blackCount = 0;
			for(int d=0; d<height&&y-d>=0; d++){
				int nx=0+d;
				int ny=y-d;
				if(chess[nx][ny] == 'B') blackCount++;
				else blackCount = 0;
				if(chess[nx][ny] == 'W') whiteCount++;
				else whiteCount = 0;
				if(blackCount >= 5) return "Black";
				if(whiteCount >= 5) return "White";
			}
		}
		
		for(int x=0; x<height; x++){ //检查副对角线方向
			whiteCount = 0;
			blackCount = 0;
			for(int d=0; x<height&&width-1-d>=0; d++){
				int nx=0+d;
				int ny=width-1-d;
				if(chess[nx][ny] == 'B') blackCount++;
				else blackCount = 0;
				if(chess[nx][ny] == 'W') whiteCount++;
				else whiteCount = 0;
				if(blackCount >= 5) return "Black";
				if(whiteCount >= 5) return "White";
			}
		}
		
		
		return "No one";
	}
	

	
	public int reckon(char color) { // 估值函数
		 
        int dx[] = {1, 0, 1, 1};  //上下
        int dy[] = {0, 1, 1, -1};
        int ans = 0;
 
        for(int x=0; x< height; x++) {
            for (int y = 0; y < width; y++) {
                if (chess[x][y] != color)
                    continue;
 
                int num[][] = new int[2][100];
 
                for (int i = 0; i < 4; i++) {  //在(x,y) 这个棋子出发，看4个方向
                    int sum = 1;
                    int flag1 = 0, flag2 = 0;
 
                    int tx = x + dx[i];
                    int ty = y + dy[i];
                    while (tx >= 0 && tx < height
                            && ty >= 0 && ty < width
                            && chess[tx][ty] == color) {   //连几个子
                        tx += dx[i];
                        ty += dy[i];
                        ++sum;
                    }
 
                    if(canBePut(tx, ty))  
                        flag1 = 1;             //为"N" 表未放，是活的
 
                    tx = x - dx[i];   //反方向
                    ty = y - dy[i];
                    while (tx >= 0 && tx < height
                            && ty >= 0 && ty < width
                            && chess[tx][ty] == color) {
                        tx -= dx[i];
                        ty -= dy[i];
                        ++sum;
                    }
 
                    if(canBePut(tx, ty))
                        flag2 = 1;
 
                    if(flag1 + flag2 > 0)
                        ++num[flag1 + flag2 - 1][sum];
                    // sum表连几子， flag表是否是活的 num[0][sum] 表示有个单活sum子的棋子， num[1][sum]表示双活sum子的棋子
                }
 
                

                //成5
                if(num[0][5] + num[1][5] > 0)
                    ans = Math.max(ans, 100000);
                    //活4 | 双死四 | 死4活3
                else if(num[1][4] > 0
                        || num[0][4] > 1
                        || (num[0][4] > 0 && num[1][3] > 0))
                    ans = Math.max(ans, 10000);
                    //双活3
                else if(num[1][3] > 1)
                    ans = Math.max(ans, 5000);
                    //死3活3
                else if(num[1][3] > 0 && num[0][3] > 0)
                    ans = Math.max(ans, 1000);
                    //死4
                else if(num[0][4] > 0)
                    ans = Math.max(ans, 500);
                    //单活3
                else if(num[1][3] > 0)
                    ans = Math.max(ans, 200);
                    //双活2
                else if(num[1][2] > 1)
                    ans = Math.max(ans, 100);
                    //死3
                else if(num[0][3] > 0)
                    ans = Math.max(ans, 50);
                    //双活2
                else if(num[1][2] > 1)
                    ans = Math.max(ans, 10);
                    //单活2
                else if(num[1][2] > 0)
                    ans = Math.max(ans, 5);
                    //死2
                else if(num[0][2] > 0)
                    ans = Math.max(ans, 1);
 
            }
        }
 
        return ans;
    }

	public int alpha_betaFind(int depth, int alpha, int beta, char color, int prex, int prey) {
		 
        if(depth >= this.depth ||  !isWin().equals("No one")) {  //递归超过深度 或者 有人赢了
 
            int ans = reckon(robotColor) - reckon(robotColor=='B' ? 'W':'B'); //分数差
 
            if(depth % 2 == 0)  
                ans = -ans;
 
            return ans;
        }
 
        for(int x=0; x < height; x++) {
            for(int y=0; y < width; y++) {
 
                if(!isEmpty(x, y))
                    continue;
 
                putChess(x, y, color);   //假设下这里
                int val = -alpha_betaFind(depth+1, -beta, -alpha, color=='B' ? 'W':'B', x, y);  //换个棋子下
 
                unMove(x, y);          //撤销假设下这里
 
                if(val >= beta)     //剪枝，已经可以赢了
                    return beta;
 
                if(val > alpha)
                    alpha = val;
            }
        }
        return alpha;
    }
 
    /* 返回AI走法 */
    public int[] getNext(char color) {
    	robotColor = color;
    	
        int rel[] = new int[2];
        int ans = -100000000;
 
        Random random = new Random();
 
 
        for(int x=0; x < height; x++) {
            for(int y=0; y < width; y++) {
 
 
                if(!isEmpty(x, y))
                    continue;
 
                putChess(x, y, color);
 
                int val = -alpha_betaFind(0, -100000000, 100000000, color=='B' ? 'W':'B', x, y);
 
                int ra = random.nextInt(100);
                if(val > ans || val == ans && ra >= 50) {
                    ans = val;
                    rel[0] = x;
                    rel[1] = y;
                }
                unMove(x, y);
            }
        }
        putChess(rel[0], rel[1], color);
        return rel;
    }
    
	public boolean canBePut(int x, int y){
		return x>=0&&x<height&&
		       y>=0&&y<width&&
		       chess[x][y]=='N';
	}


	public boolean isWithComputer() {
		return withComputer;
	}


	public void setWithComputer(boolean withComputer) {
		this.withComputer = withComputer;
	}


}


