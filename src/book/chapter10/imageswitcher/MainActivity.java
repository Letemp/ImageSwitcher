package book.chapter10.imageswitcher;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements OnClickListener{

	private ImageSwitcher switcher;
	private Button next,last;
		
	/*�洢ͼƬ��Դ,��ͼƬ��Դ���ص�ArrayList��*/
	final List<Drawable> list = new ArrayList<Drawable>();
	
	int index = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		switcher=(ImageSwitcher)this.findViewById(R.id.imageSwitcher);
		next=(Button)this.findViewById(R.id.next);
		last=(Button)this.findViewById(R.id.last);
		next.setOnClickListener(this);
		last.setOnClickListener(this);
		
		/*setFactory�������ڴ���������ͼ���Ӷ�����������ͼ������л�,Ҫ��ͼƬ��ʾ��ImageSwitcher�ؼ��У�
		  ����ΪImageSwitcher������һ��ViewFactory����������ʾ��ͼƬ�͸��������ֿ���*/
		
		switcher.setFactory(new ViewFactory(){//����ViewFactory����

			/*���⣬���ǻ���Ҫʵ��ViewSwitcher.ViewFactory�ӿ��е�makeView()���󷽷���ͨ���÷������Է���һ��ImageView����
			 ����ͼƬ����ͨ����ImageView������������ʾ��*/
			
			@Override
			public View makeView() {//�½�һ����ͼ
				// TODO Auto-generated method stub
				ImageView imageView=new ImageView(MainActivity.this);//����һ��ImageView
				imageView.setBackgroundColor(0XFFCCCC);//���ÿؼ�������ɫ
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//����ͼƬ��������
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));//����ͼƬ�Ŀ�͸�
				return imageView;//���ش�����imageView
			}
		});
		
		
		list.add(getResources().getDrawable(R.drawable.bee));
		list.add(getResources().getDrawable(R.drawable.girl));
		list.add(getResources().getDrawable(R.drawable.hanberger));
		
		//��ȡͼƬ��Դ
		switcher.setImageResource(R.drawable.bee);
		switcher.setImageResource(R.drawable.girl);
		switcher.setImageResource(R.drawable.hanberger);
		
		
		/*
		 * ���ͼƬ������л�
		switcher.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
			    }
		});
		
		
		next.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switcher.setImageResource(R.drawable.bee);
			}
			
		});
		
		last.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switcher.setImageResource(R.drawable.girl);
			}
			
		});
		*/
		
		//����Ч��
		switcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
		//����Ч��
		switcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				
	    switch (v.getId()) {
	    //��һ��
	    case R.id.last:
	    	index--;
	    	if (index < 0) {
	    		index = list.size()-1;
	    		}
	    	//switcher.setImageDrawable(getResources().getDrawable(R.drawable.girl));
	    	//System.out.println("-------------------"+index+"----"+list.get(index));
	    	switcher.setImageDrawable(list.get(index));
	    	break;
	    	
	    //��һ��
	    case R.id.next:
	    	index++;
	    	if (index >= list.size()) {
	    		index = 0;
	    		}
	    	switcher.setImageDrawable(list.get(index));
	    	break;
	    	}
	}

}
