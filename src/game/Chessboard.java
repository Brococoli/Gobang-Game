package game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;






public class Chessboard {
	
	private char[][] chess;   //����
	private int width, height;  //��Ⱥ͸߶�
	private boolean withComputer = true;  //�Ƿ�͵���
	private int depth = 1;   //����С���������
	private char robotColor;
	private LinkedList<Integer> chessList; //�����Ѿ��¹������ӣ���Ϊ���ñ�����ɫ������Ҫ�������һ��������
	
	public void popPoint(){  //�Ƴ����һ������
		int x=0, y=0;
		try{
			y = chessList.removeLast();
			x = chessList.removeLast();
			chess[x][y] = 'N';
		}catch(NoSuchElementException e){
			System.out.println("color �޷��ٻ���");
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
	public void putChess(int x, int y, char who) {   //������
		chessList.add(x);
		chessList.add(y);
		chess[x][y] = who;
	}
	

	public void setSize(int width, int height) {  //���óߴ�
		this.width = width;
		this.height = height;
	}




	/*  �жϸ�λ���Ƿ����� */
    public boolean isEmpty(int x, int y) {
        return chess[x][y] == 'N';
    }
 
    
    /* ���� */
    public void unMove(int x, int y) {
    	popPoint();
        chess[x][y] = 'N';
    }

	public void initChess(){  //��ʼ������
		chess = new char[width+5][height+5];
		for(int i=0;i<width; i++){
			Arrays.fill(chess[i],'N');
		}
		chessList = new LinkedList<Integer>(); //��ʼ��List
	}
	

	public String isWin(){  //�ж��Ƿ�����Ӯ��"No one" ��û���ˣ� "Black" �����Ӯ�� "White" �����Ӯ
		//judge
		int whiteCount;
		int blackCount;
		for(int x=0; x<height; x++){  //���ˮƽ����
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
		
		for(int y=0; y<width; y++){  //�����ֱ����
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
		
		for(int x=0; x<height; x++){ //������Խ��߷���
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
		
		for(int y=0; y<width; y++){ //������Խ��߷���
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
		
		for(int y=0; y<width; y++){ //��鸱�Խ��߷���
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
		
		for(int x=0; x<height; x++){ //��鸱�Խ��߷���
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
	

	
	public int reckon(char color) { // ��ֵ����
		 
        int dx[] = {1, 0, 1, 1};  //����
        int dy[] = {0, 1, 1, -1};
        int ans = 0;
 
        for(int x=0; x< height; x++) {
            for (int y = 0; y < width; y++) {
                if (chess[x][y] != color)
                    continue;
 
                int num[][] = new int[2][100];
 
                for (int i = 0; i < 4; i++) {  //��(x,y) ������ӳ�������4������
                    int sum = 1;
                    int flag1 = 0, flag2 = 0;
 
                    int tx = x + dx[i];
                    int ty = y + dy[i];
                    while (tx >= 0 && tx < height
                            && ty >= 0 && ty < width
                            && chess[tx][ty] == color) {   //��������
                        tx += dx[i];
                        ty += dy[i];
                        ++sum;
                    }
 
                    if(canBePut(tx, ty))  
                        flag1 = 1;             //Ϊ"N" ��δ�ţ��ǻ��
 
                    tx = x - dx[i];   //������
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
                    // sum�������ӣ� flag���Ƿ��ǻ�� num[0][sum] ��ʾ�и�����sum�ӵ����ӣ� num[1][sum]��ʾ˫��sum�ӵ�����
                }
 
                

                //��5
                if(num[0][5] + num[1][5] > 0)
                    ans = Math.max(ans, 100000);
                    //��4 | ˫���� | ��4��3
                else if(num[1][4] > 0
                        || num[0][4] > 1
                        || (num[0][4] > 0 && num[1][3] > 0))
                    ans = Math.max(ans, 10000);
                    //˫��3
                else if(num[1][3] > 1)
                    ans = Math.max(ans, 5000);
                    //��3��3
                else if(num[1][3] > 0 && num[0][3] > 0)
                    ans = Math.max(ans, 1000);
                    //��4
                else if(num[0][4] > 0)
                    ans = Math.max(ans, 500);
                    //����3
                else if(num[1][3] > 0)
                    ans = Math.max(ans, 200);
                    //˫��2
                else if(num[1][2] > 1)
                    ans = Math.max(ans, 100);
                    //��3
                else if(num[0][3] > 0)
                    ans = Math.max(ans, 50);
                    //˫��2
                else if(num[1][2] > 1)
                    ans = Math.max(ans, 10);
                    //����2
                else if(num[1][2] > 0)
                    ans = Math.max(ans, 5);
                    //��2
                else if(num[0][2] > 0)
                    ans = Math.max(ans, 1);
 
            }
        }
 
        return ans;
    }

	public int alpha_betaFind(int depth, int alpha, int beta, char color, int prex, int prey) {
		 
        if(depth >= this.depth ||  !isWin().equals("No one")) {  //�ݹ鳬����� ���� ����Ӯ��
 
            int ans = reckon(robotColor) - reckon(robotColor=='B' ? 'W':'B'); //������
 
            if(depth % 2 == 0)  
                ans = -ans;
 
            return ans;
        }
 
        for(int x=0; x < height; x++) {
            for(int y=0; y < width; y++) {
 
                if(!isEmpty(x, y))
                    continue;
 
                putChess(x, y, color);   //����������
                int val = -alpha_betaFind(depth+1, -beta, -alpha, color=='B' ? 'W':'B', x, y);  //����������
 
                unMove(x, y);          //��������������
 
                if(val >= beta)     //��֦���Ѿ�����Ӯ��
                    return beta;
 
                if(val > alpha)
                    alpha = val;
            }
        }
        return alpha;
    }
 
    /* ����AI�߷� */
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


