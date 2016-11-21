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
		
	/*存储图片资源,将图片资源加载到ArrayList中*/
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
		
		/*setFactory方法用于创建两个视图，从而在这两个视图间进行切换,要将图片显示在ImageSwitcher控件中，
		  必须为ImageSwitcher类设置一个ViewFactory，用来将显示的图片和父窗口区分开来*/
		
		switcher.setFactory(new ViewFactory(){//设置ViewFactory对象

			/*此外，我们还需要实现ViewSwitcher.ViewFactory接口中的makeView()抽象方法，通过该方法可以返回一个ImageView对象，
			 所有图片都将通过该ImageView对象来进行显示。*/
			
			@Override
			public View makeView() {//新建一个视图
				// TODO Auto-generated method stub
				ImageView imageView=new ImageView(MainActivity.this);//创建一个ImageView
				imageView.setBackgroundColor(0XFFCCCC);//设置控件背景颜色
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//设置图片缩放类型
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));//设置图片的宽和高
				return imageView;//返回创建的imageView
			}
		});
		
		
		list.add(getResources().getDrawable(R.drawable.bee));
		list.add(getResources().getDrawable(R.drawable.girl));
		list.add(getResources().getDrawable(R.drawable.hanberger));
		
		//获取图片资源
		switcher.setImageResource(R.drawable.bee);
		switcher.setImageResource(R.drawable.girl);
		switcher.setImageResource(R.drawable.hanberger);
		
		
		/*
		 * 点击图片本身的切换
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
		
		//淡入效果
		switcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
		//淡出效果
		switcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
				
	    switch (v.getId()) {
	    //上一张
	    case R.id.last:
	    	index--;
	    	if (index < 0) {
	    		index = list.size()-1;
	    		}
	    	//switcher.setImageDrawable(getResources().getDrawable(R.drawable.girl));
	    	//System.out.println("-------------------"+index+"----"+list.get(index));
	    	switcher.setImageDrawable(list.get(index));
	    	break;
	    	
	    //下一张
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
