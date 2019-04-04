import java.math.BigInteger;

public class Main {


	//��̺���
	public static int S(int X) {
		return X+1;
	}
	//�㺯��
	public static int N(int X) {
		return 0;
	}
	//ͶӰ������ 1 <= i<= n
	public static int U(int i,int[] X) throws Exception{
		if(i>0 && i<=X.length) return X[i-1];
		else throw new Exception();
	}
	//1. X+Y
	public static int add(int X, int Y) {
		if(Y==0) return X;
		return S(add(X,Y-1));
	}
	//2. X*Y
	public static int mul(int X,int Y) {
		if(Y==0) return 0;
		return mul(X,Y-1)+X;
	}
	//3. n! ��εݹ���ø÷��������ջ�����
	public static int fac(int X) {
		if(X==0) return 1;
		else return fac(X-1)*X;
	}
	public static int Factoria(int x) {
		if(x==0) return 1;
		int s = 1;
		for(int i=1;i<=x;i++) {
			s *= i;
		}
		return s;
	}
	//4. X^Y
	public static int exp(int X,int Y) {
		if(Y==0) return 1;
		return mul(exp(X,Y-1),X);
	}
	//5. P(X) ǰ������������ֵΪX-1����X=0������ֵΪ0��
	public static int P(int X) {
		if(X>0) return X-1;
		return 0;
	}
	//6. X��Y
	public static int sub(int X,int Y) {
		if(Y==0) return X;
		return P(sub(X,Y-1));
	}
	//7. |X-Y|
	public static int abs(int X,int Y) {
		return sub(X,Y)+sub(Y,X);
	}
	//8. ��(X) ��X==0�� ��(X)=1������Ϊ0
	public static int ��(int X) {
		return sub(1,X);
	}
	//9. ��t��Y f(X1,��, Xn, t) 
	public static int h(int[] X,int Y) {
		if(Y==0) return 0;
		return add(h(X,Y-1),X[Y-1]);
	}
	//10. ��t��Y f(X1,��, Xn, t�� 
	public static int g(int[] X,int Y) {
		if(Y==0) return 1;
		return mul(g(X,Y-1),X[Y-1]);
	}
	//11. d(X, Y)����Ϊ���� X=Y��d(X, Y) �� 0   �� X��Y��d(X, Y) �� 1 
	public static int d(int X,int Y) {
		return ��(��(abs(X,Y)));
	}
	//12. X��Y
	public static boolean X_qual_Y(int X,int Y){
		if(d(X,Y)==0) return true;
		return false;
	}
	//13. X>Y
	public static boolean X_greater_Y(int X,int Y) {
		if(��(sub(X,Y))==0) return true;
		return false;
	}
	//14. X<=Y
	public static boolean X_lessORequal_Y(int X,int Y) {
		return !X_greater_Y(X,Y);
	}
	//15. Y�OX  	_______��Y����X����ν�� 
	public static boolean Y_divide_X(int X,int Y){
		int t=0;
		while(t<=X) {
			if(mul(Y,t)==X) return true;
			t=S(t);
		}
		return false;
	}
	//16. [Y/X] ________����ֵ��X����Y���� 
	public static int Y_quotient_X(int X,int Y) {
		int t=0;
		while(t<=Y) {
			if(X_greater_Y(mul(X,t+1), Y)) return t;
			t=S(t);	
		}
		return t;
	}
	//17. Prim(X)  _______��X����������ν��
	public static boolean prim(int X) {
		if(X<=1) return false;
		int t=1;
		while(t<=X) {
			if(t!=1 && t!=X && X%t==0) return false;
			t=S(t);	
		}
		return true;
	}
	//18. Pi ��������ֵ�ǵ�i������ (Լ�� P0��0�ǵ�0�������� P1��2�ǵ�1�������� P2��3�ǵ�2�������� P3 ��5�ǵ�3������)
	public static int Pi(int i) {
		if(i==0) return 0;
		if(i==1) return 2;
		int j=2,n=0;
		while(n<i) {
			if(prim(j)) n++;
			j++;
		}
		return j-1;
	}
	//19. R(X, Y)   	____����ֵ��Y��Xʱ������
	public static int R(int X,int Y) {
		if(Y>X) return X;
		return X-Y*Y_quotient_X(Y, X);
	}
	//20. t(X)  ��������ֵΪ��X�������ӷֽ��з���ָ���ĸ�����t(0)����Ϊ0������20��223051 �� t(20)��2
	public static int t(int X) {
		return Lh(X,X);
	}
	//����Lh(i, X)Ϊǰi��1��������������X�������ĸ�����
	public static int Lh(int i,int X) {
		if(i==0) return 0;
		if(X%Pi(i)==0) return Lh(i-1,X)+1;
		return Lh(i-1,X);
	}
	//21. (X)i ��������ֵΪX�������ӷֽ��� ��i��������ָ������Pi��ָ
	public static int Xi(int X,int i) {
		if(i==0) return 0;
		int Pi = Pi(i);
		for(int t=0;t<=X;t++) {
			if(X%exp(Pi,t)==0 && X%exp(Pi,t+1)!=0) return t;
		}
		return 0;
	}
	//22. Lt(X)   ��������ֵΪX�����ӷֽ��У���0ָ����������������
	public static int Lt(int X) {
		int temp = 0;
		for(int i=1;i<=X;i++) {
			int sushu = Pi(i);
			if(sushu<=X && X%sushu==0) temp=i;
		}
		return temp;
	}
	//23. GN(X)    ν�� ����ν�ʱ�ʾ����������i��Lt(X)����(X)i��0�� ��ν��GN(X)Ϊ�档 ����X�������ӷֽ���û����ָ��ʱ��ν��Ϊ��
	
	
	
	public static void main(String[] args) {
		System.out.println("1. 2+3="+add(2,3));
		System.out.println("2. 2*3="+mul(2,3));
		System.out.println("3. 3!="+fac(3));
		System.out.println("4. 2^3="+exp(2,3));
		System.out.println("5. "+P(3));
		System.out.println(sub(4,1));
		System.out.println(abs(3,5));
		System.out.println("��(5)="+ ��(5)+"    ��(0)="+ ��(0));
		int[] t = {1,2,5,3};
		System.out.println(h(t,3));
		System.out.println(g(t,3));
		System.out.println("11. d(2,3)="+d(2,3)+"    d(2,2)="+d(2,2));
		System.out.println("12. 2=3? "+X_qual_Y(2, 3));
		System.out.println("13. 2>3? "+X_greater_Y(2, 3));
		System.out.println("14. 2<=3? "+X_lessORequal_Y(2, 3));
		System.out.println("15. 3����12�� "+Y_divide_X(12, 3));
		System.out.println("16. 2����4���̣�"+Y_quotient_X(2,4)+"  3����4���̣�"+Y_quotient_X(3,4)+"  5����4���̣�"+Y_quotient_X(5,4));
		System.out.println("17. 5��������"+prim(5)+"  6��������"+prim(6));
		System.out.println("18. ��6��������"+Pi(6));
		System.out.println("19. 7%4="+R(7,4));
		System.out.println("20. t(20)="+t(20));
		System.out.println("21. 20�����ӷֽ��е�3������(��5)��ָ��:"+Xi(20,3));
		System.out.println("22. Lt(20)="+Lt(20)+"  Lt(60)="+Lt(60));
	}
}
