package minor.uno;


import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import minor.bean.CardInfoBean;
import minor.network.ChatClsSocket;
import minor.network.ChatSendData;
import minor.network.ClsSocket;
import minor.network.SendData;
import minor.util.CardMapper;
import android.app.Activity;
import android.app.Dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

//protected static final int RESULT_SPEECH = 1;
private ImageView tImageView1, tImageView2, tImageView3, tImageView4,
			tImageView5, tImageView6, tImageView7, tImageView8, tImageView9, tImageView10;
	private ImageView bImageView1, bImageView2, bImageView3, bImageView4,
			bImageView5, bImageView6, bImageView7, bImageView8, bImageView9, bImageView10;
	private ImageView tdeck;
	private ImageView arr[]=new ImageView[10];
	private ImageView a1[]=new ImageView[10];
	private Button chat;
	private ImageView center;
	public static List<CardInfoBean> cardList = new ArrayList<CardInfoBean>();
	private int currentCardFaceValue, currentCardImage;
	public String currentCardColor;
	private int topCardFaceValue, topCardImage;
	private String topCardColor;
	final Context context = this;
	TextView textview,textview2 ;
	public static int j;
	static Socket socket=null;
	public static ArrayList p1= new ArrayList();
	public static Stack d1 = new Stack();
	public static Stack c1 = new Stack();
	public static String chatMsg=null;
	public static Integer o1=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
		textview = (TextView) findViewById(R.id.textView1);
		textview2 = (TextView) findViewById(R.id.text2);
		chat= (Button) findViewById(R.id.chat);
		tImageView1 = (ImageView) findViewById(R.id.timageView1);
		tImageView2 = (ImageView) findViewById(R.id.timageView2);
		tImageView3 = (ImageView) findViewById(R.id.timageView3);
		tImageView4 = (ImageView) findViewById(R.id.timageView4);
		tImageView5 = (ImageView) findViewById(R.id.timageView5);
		tImageView6 = (ImageView) findViewById(R.id.timageView6);
		tImageView7 = (ImageView) findViewById(R.id.timageView7);
		tImageView8 = (ImageView) findViewById(R.id.timageView8);
		tImageView9 = (ImageView) findViewById(R.id.timageView9);
		tImageView10 = (ImageView) findViewById(R.id.timageView10);
		tdeck=(ImageView)findViewById(R.id.imageView1);
			
		bImageView1= (ImageView) findViewById(R.id.ImageView_below_1);
		bImageView2 = (ImageView) findViewById(R.id.ImageView_below_2);
		bImageView3 = (ImageView) findViewById(R.id.ImageView_below_3);
		bImageView4 = (ImageView) findViewById(R.id.ImageView_below_4);
		bImageView5 = (ImageView) findViewById(R.id.ImageView_below_5);
		bImageView6 = (ImageView) findViewById(R.id.ImageView_below_6);
		bImageView7 = (ImageView) findViewById(R.id.ImageView_below_7);
		bImageView8 = (ImageView) findViewById(R.id.ImageView_below_8);
		bImageView9 = (ImageView) findViewById(R.id.ImageView_below_9);
		bImageView10 = (ImageView) findViewById(R.id.ImageView_below_10);
		center = (ImageView) findViewById(R.id.imageView2);
		
		ClsSocket rSocket = new ClsSocket(this);
		rSocket.connectSocket();
		
		ChatClsSocket cSocket = new ChatClsSocket(this);
		cSocket.connectSocket();
		
		cardMappingWithAttributes();
		setClickListner();

		Animation anim = AnimationUtils.loadAnimation(this,
				R.anim.sticker_fall);
		anim.setAnimationListener(animListner);
;
		
		tImageView1.startAnimation(anim);
		tImageView2.startAnimation(anim);
		tImageView3.startAnimation(anim);
		tImageView4.startAnimation(anim);
		tImageView5.startAnimation(anim);
		tImageView6.startAnimation(anim);
		tImageView7.startAnimation(anim);
		
		bImageView1.startAnimation(anim);	
		bImageView2.startAnimation(anim);	
		bImageView3.startAnimation(anim);	
		bImageView4.startAnimation(anim);	
		bImageView5.startAnimation(anim);	
		bImageView6.startAnimation(anim);
		bImageView7.startAnimation(anim);
		

	}

	AnimationListener animListner = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animation arg0) {
			// TODO Auto-generated method stub

		}
	};

	private void setClickListner() {
	
			bImageView1.setOnClickListener(clickListener);
			bImageView2.setOnClickListener(clickListener);
			bImageView3.setOnClickListener(clickListener);
			bImageView4.setOnClickListener(clickListener);
			bImageView5.setOnClickListener(clickListener);
			bImageView6.setOnClickListener(clickListener);
			bImageView7.setOnClickListener(clickListener);
			bImageView8.setOnClickListener(clickListener);
			bImageView9.setOnClickListener(clickListener);
			bImageView10.setOnClickListener(clickListener);
			chat.setOnClickListener(chatClick);
		   tdeck.setOnClickListener(clickdeck);
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			Log.e("MainActivity", " Clicked on" + arg0.getTag());
			int m= (Integer) arg0.getTag();
			CardInfoBean cib = cardList.get(Integer.parseInt(arg0.getTag().toString()));
			
			currentCardImage = cib.getCardImage();
			currentCardColor = cib.getCardColor();
			currentCardFaceValue = cib.getCardValue();

			System.out.println("Current Card Image: " + currentCardImage
					+ " currentCardColor: " + currentCardColor
					+ " currentCardFaceValue: " + currentCardFaceValue);
			int x=check (currentCardColor, currentCardFaceValue);
	
			if(x==1)
			{	
				
				new SendData(m, MainActivity.this);
			}
			else
			
			{
				Toast.makeText(getApplicationContext(), "Move Not Possible", 3000).show();
			}	
			
		}

};
	
	
              
              
	
	
	int check(String a, int b)
	{
		
		CardInfoBean topcard = cardList.get(Integer.parseInt(c1.peek().toString()));

		topCardImage = topcard.getCardImage();
		topCardColor = topcard.getCardColor();
		topCardFaceValue = topcard.getCardValue();

		System.out.println("Top Card Image: " + topCardImage
				+ " topCardColor: " + topCardColor
				+ " topCardFaceValue: " + topCardFaceValue);
	
	 if ((a==topCardColor) || (b==topCardFaceValue))
	 {
		 return 1;
	 }
	 else
	 {		 return 0;
	 } 
}
	OnClickListener clickdeck=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			new SendData(200, MainActivity.this);
			
		}
	};
	void allocateCard() {

		topCards();
		arr[0]=bImageView1;
		arr[1]=bImageView2;
		arr[2]=bImageView3;
		arr[3]=bImageView4;
		arr[4]=bImageView5;
		arr[5]=bImageView6;
		arr[6]=bImageView7;
		arr[7]=bImageView8;
		arr[8]=bImageView9;
		arr[9]=bImageView10;
		
		p1.trimToSize();
		for(int i=0;i<p1.size();i++)
		{	
		arr[i].setBackgroundResource(CardMapper.cardName[(Integer) p1.get(i)]);
		arr[i].setTag((Integer) p1.get(i));
		}
		//Toast.makeText(getApplicationContext(), c1.toString(), 2000).show();
		if(p1.size()==10)
		{
			arr[9].setVisibility(View.VISIBLE);
			arr[8].setVisibility(View.VISIBLE);
			arr[7].setVisibility(View.VISIBLE);
			arr[6].setVisibility(View.VISIBLE);
			arr[5].setVisibility(View.VISIBLE);
			arr[4].setVisibility(View.VISIBLE);
			arr[3].setVisibility(View.VISIBLE);
			arr[2].setVisibility(View.VISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
		}
		else if(p1.size()==8)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.VISIBLE);
			arr[7].setVisibility(View.VISIBLE);
			arr[6].setVisibility(View.VISIBLE);
			arr[5].setVisibility(View.VISIBLE);
			arr[4].setVisibility(View.VISIBLE);
			arr[3].setVisibility(View.VISIBLE);
			arr[2].setVisibility(View.VISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
		}
		
		else if(p1.size()==8)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.VISIBLE);
			arr[6].setVisibility(View.VISIBLE);
			arr[5].setVisibility(View.VISIBLE);
			arr[4].setVisibility(View.VISIBLE);
			arr[3].setVisibility(View.VISIBLE);
			arr[2].setVisibility(View.VISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
		}
		
		else if(p1.size()==7)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.INVISIBLE);
			arr[6].setVisibility(View.VISIBLE);
			arr[5].setVisibility(View.VISIBLE);
			arr[4].setVisibility(View.VISIBLE);
			arr[3].setVisibility(View.VISIBLE);
			arr[2].setVisibility(View.VISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
		}
		
		else if(p1.size()==6)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.INVISIBLE);
			arr[6].setVisibility(View.INVISIBLE);
			arr[5].setVisibility(View.VISIBLE);
			arr[4].setVisibility(View.VISIBLE);
			arr[3].setVisibility(View.VISIBLE);
			arr[2].setVisibility(View.VISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
		}	
		else if(p1.size()==5)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.INVISIBLE);
			arr[6].setVisibility(View.INVISIBLE);
			arr[5].setVisibility(View.INVISIBLE);
			arr[4].setVisibility(View.VISIBLE);
			arr[3].setVisibility(View.VISIBLE);
			arr[2].setVisibility(View.VISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
			
		}
		else if(p1.size()==4)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.INVISIBLE);
			arr[6].setVisibility(View.INVISIBLE);
			arr[5].setVisibility(View.INVISIBLE);
			arr[4].setVisibility(View.INVISIBLE);
			arr[3].setVisibility(View.VISIBLE);
			arr[2].setVisibility(View.VISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
		}
		else if(p1.size()==3)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.INVISIBLE);
			arr[6].setVisibility(View.INVISIBLE);
			arr[5].setVisibility(View.INVISIBLE);
			arr[4].setVisibility(View.INVISIBLE);
			arr[3].setVisibility(View.INVISIBLE);
			arr[2].setVisibility(View.VISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
		}
		else if(p1.size()==2)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.INVISIBLE);
			arr[6].setVisibility(View.INVISIBLE);
			arr[5].setVisibility(View.INVISIBLE);
			arr[4].setVisibility(View.INVISIBLE);
			arr[3].setVisibility(View.INVISIBLE);
			arr[2].setVisibility(View.INVISIBLE);
			arr[1].setVisibility(View.VISIBLE);
			arr[0].setVisibility(View.VISIBLE);
			
		}
		else if(p1.size()==1)
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.INVISIBLE);
			arr[6].setVisibility(View.INVISIBLE);
			arr[5].setVisibility(View.INVISIBLE);
			arr[4].setVisibility(View.INVISIBLE);
			arr[3].setVisibility(View.INVISIBLE);
			arr[2].setVisibility(View.INVISIBLE);
			arr[1].setVisibility(View.INVISIBLE);
			arr[0].setVisibility(View.VISIBLE);
		}
		else
		{
			arr[9].setVisibility(View.INVISIBLE);
			arr[8].setVisibility(View.INVISIBLE);
			arr[7].setVisibility(View.INVISIBLE);
			arr[6].setVisibility(View.INVISIBLE);
			arr[5].setVisibility(View.INVISIBLE);
			arr[4].setVisibility(View.INVISIBLE);
			arr[3].setVisibility(View.INVISIBLE);
			arr[2].setVisibility(View.INVISIBLE);
			arr[1].setVisibility(View.INVISIBLE);
			arr[0].setVisibility(View.INVISIBLE);
			Toast.makeText(getApplicationContext(), "You Won this game...!!!!!Congrats", 8000).show();
		}	
		
		
		center.setBackgroundResource(CardMapper.cardName[(Integer) c1.peek()]);
		//Toast.makeText(getApplicationContext(), o1.toString(), 10000).show();
		textview2.setText("Player cards : " +o1.toString());
	}
	public void topCards()
	{
		a1[0]=tImageView1;
		a1[1]=tImageView2;
		a1[2]=tImageView3;
		a1[3]=tImageView4;
		a1[4]=tImageView5;
		a1[5]=tImageView6;
		a1[6]=tImageView7;
		a1[7]=tImageView7;
		a1[8]=tImageView7;
		a1[9]=tImageView7;
		if(o1==10)
		{
			a1[9].setVisibility(View.VISIBLE);
			a1[8].setVisibility(View.VISIBLE);
			a1[7].setVisibility(View.VISIBLE);
			a1[6].setVisibility(View.VISIBLE);
			a1[5].setVisibility(View.VISIBLE);
			a1[4].setVisibility(View.VISIBLE);
			a1[3].setVisibility(View.VISIBLE);
			a1[2].setVisibility(View.VISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
		}
		else if(o1==9)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.VISIBLE);
			a1[7].setVisibility(View.VISIBLE);
			a1[6].setVisibility(View.VISIBLE);
			a1[5].setVisibility(View.VISIBLE);
			a1[4].setVisibility(View.VISIBLE);
			a1[3].setVisibility(View.VISIBLE);
			a1[2].setVisibility(View.VISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
		}
		else if(o1==8)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.VISIBLE);
			a1[6].setVisibility(View.VISIBLE);
			a1[5].setVisibility(View.VISIBLE);
			a1[4].setVisibility(View.VISIBLE);
			a1[3].setVisibility(View.VISIBLE);
			a1[2].setVisibility(View.VISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
		}
		
		else if(o1==7)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.INVISIBLE);
			a1[6].setVisibility(View.VISIBLE);
			a1[5].setVisibility(View.VISIBLE);
			a1[4].setVisibility(View.VISIBLE);
			a1[3].setVisibility(View.VISIBLE);
			a1[2].setVisibility(View.VISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
		}
		
		else if(o1==6)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.INVISIBLE);
			a1[6].setVisibility(View.INVISIBLE);
			a1[5].setVisibility(View.VISIBLE);
			a1[4].setVisibility(View.VISIBLE);
			a1[3].setVisibility(View.VISIBLE);
			a1[2].setVisibility(View.VISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
		}	
		else if(o1==5)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.INVISIBLE);
			a1[6].setVisibility(View.INVISIBLE);
			a1[5].setVisibility(View.INVISIBLE);
			a1[4].setVisibility(View.VISIBLE);
			a1[3].setVisibility(View.VISIBLE);
			a1[2].setVisibility(View.VISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
			
		}
		else if(o1==4)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.INVISIBLE);
			a1[6].setVisibility(View.INVISIBLE);
			a1[5].setVisibility(View.INVISIBLE);
			a1[4].setVisibility(View.INVISIBLE);
			a1[3].setVisibility(View.VISIBLE);
			a1[2].setVisibility(View.VISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
		}
		else if(o1==3)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.INVISIBLE);
			a1[6].setVisibility(View.INVISIBLE);
			a1[5].setVisibility(View.INVISIBLE);
			a1[4].setVisibility(View.INVISIBLE);
			a1[3].setVisibility(View.INVISIBLE);
			a1[2].setVisibility(View.VISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
		}
		else if(o1==2)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.INVISIBLE);
			a1[6].setVisibility(View.INVISIBLE);
			a1[5].setVisibility(View.INVISIBLE);
			a1[4].setVisibility(View.INVISIBLE);
			a1[3].setVisibility(View.INVISIBLE);
			a1[2].setVisibility(View.INVISIBLE);
			a1[1].setVisibility(View.VISIBLE);
			a1[0].setVisibility(View.VISIBLE);
			
		}
		else if(o1==1)
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.INVISIBLE);
			a1[6].setVisibility(View.INVISIBLE);
			a1[5].setVisibility(View.INVISIBLE);
			a1[4].setVisibility(View.INVISIBLE);
			a1[3].setVisibility(View.INVISIBLE);
			a1[2].setVisibility(View.INVISIBLE);
			a1[1].setVisibility(View.INVISIBLE);
			a1[0].setVisibility(View.VISIBLE);
		}
		else
		{
			a1[9].setVisibility(View.INVISIBLE);
			a1[8].setVisibility(View.INVISIBLE);
			a1[7].setVisibility(View.INVISIBLE);
			a1[6].setVisibility(View.INVISIBLE);
			a1[5].setVisibility(View.INVISIBLE);
			a1[4].setVisibility(View.INVISIBLE);
			a1[3].setVisibility(View.INVISIBLE);
			a1[2].setVisibility(View.INVISIBLE);
			a1[1].setVisibility(View.INVISIBLE);
			a1[0].setVisibility(View.INVISIBLE);
			Toast.makeText(getApplicationContext(), "Opposite Player Won....:(", 20000).show();
			
		}
	}

	public void cardMappingWithAttributes() {
		for (int i = 0; i < CardMapper.cardName.length; i++) {
			CardInfoBean ci = new CardInfoBean();
			ci.setCardImage(CardMapper.cardName[i]);
			if (i <= 10)
				{
				ci.setCardColor("blue");
				ci.setCardValue(i);
				}
			else if (i > 10 && i <= 21)
			{
				ci.setCardValue(i-11);
				ci.setCardColor("green");
			}
			else if (i > 21 && i <= 32)
			{
				ci.setCardValue(i-22);
				ci.setCardColor("red");
			}	
			else if (i > 32 && i <= 43)
			{	
				ci.setCardColor("Yellow");
				ci.setCardValue(i-33);
			}	
			cardList.add(ci);
		}
	 
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if(j==0)
			h.sendMessage(new android.os.Message());
		else
		textview.setText(intent.getExtras().getString("data"));
		
		
	}
	Handler h=new Handler(){
		public void handleMessage(android.os.Message msg) {
		
			allocateCard();
		
		};
	};
	OnClickListener chatClick=new OnClickListener() {
		
		
		@Override
		public void onClick(View arg0) {

			// create a Dialog component
			final Dialog dialog = new Dialog(context);
			
			//tell the Dialog to use the dialog.xml as it's layout description
			dialog.setContentView(R.layout.dialog);
			dialog.setTitle("Send Msg");

			final EditText txt = (EditText) dialog.findViewById(R.id.txt);
			
			//txt.setText("");
			
		
			Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);
			Button dialogButton2 = (Button) dialog.findViewById(R.id.dialogButton2);
			
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			dialogButton2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String msg= txt.getText().toString();
					new ChatSendData(msg,MainActivity.this);
					dialog.dismiss();
				}
			});

			dialog.show();
		}
	};

	}
