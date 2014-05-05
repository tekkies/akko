package uk.co.tekkies.akko.groundstation;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PlotView extends View {
	Paint paint;
	MotionEvent motionEvent=null;
	ArrayList<PointF> points=new ArrayList<PointF>();
	RectF realExtent=null;

	public PlotView(Context context) {
		super(context);
		initialize(context);
	}

	public PlotView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
	}
	
	private void initialize(Context context) {
		createSampleData();
		setupPalette(context);
	}

	private void setupPalette(Context context) {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setARGB(0x80, 0x00, 0x00, 0xff);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(isInEditMode()) {
			canvas.drawRect(new Rect(0,0,100,100), paint);
		} else {
			for(int i=0;i<points.size()-1;i++) {
				//canvas.drawPoint(points.get(i).x+50, points.get(i).y, paint);
				//canvas.drawLine(points.get(i).x+50, points.get(i).y,points.get(i+1).x+50, points.get(i+1).y, paint);
				canvas.drawPoint(100+(points.get(i).x-realExtent.left)/realExtent.width()*100, 
								 100-(points.get(i).y-realExtent.top)/realExtent.height()*100, 
								 paint);
			}
		}
	}

	
	private void createSampleData() {
		
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08851265907287,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735685825347);
		addSample(-4.08850729465484,51.5735739469528);
		addSample(-4.08850729465484,51.5735739469528);
		addSample(-4.08850729465484,51.5735739469528);
		addSample(-4.08850729465484,51.5735739469528);
		addSample(-4.08850729465484,51.5735739469528);
		addSample(-4.08850729465484,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08851265907287,51.5735739469528);
		addSample(-4.08850729465484,51.5735739469528);
		addSample(-4.08850729465484,51.5735739469528);
		addSample(-4.08850193023681,51.5735632181167);
		addSample(-4.08850193023681,51.5735632181167);
		addSample(-4.08848583698272,51.5735524892807);
		addSample(-4.08848583698272,51.5735524892807);
		addSample(-4.08846974372863,51.5735363960266);
		addSample(-4.08846974372863,51.5735363960266);
		addSample(-4.08845365047454,51.5735203027725);
		addSample(-4.08845365047454,51.5735203027725);
		addSample(-4.08844828605651,51.5735095739364);
		addSample(-4.08844828605651,51.5735095739364);
		addSample(-4.08843219280242,51.5734934806823);
		addSample(-4.08843219280242,51.5734934806823);
		addSample(-4.08841609954833,51.5734827518463);
		addSample(-4.08841609954833,51.5734827518463);
		addSample(-4.08841073513031,51.5734827518463);
		addSample(-4.08841073513031,51.5734827518463);
		addSample(-4.08840000629425,51.5734666585922);
		addSample(-4.08840000629425,51.5734666585922);
		addSample(-4.08838927745819,51.5734559297561);
		addSample(-4.08838927745819,51.5734559297561);
		addSample(-4.08837854862213,51.573439836502);
		addSample(-4.08837854862213,51.573439836502);
		addSample(-4.08836781978607,51.573429107666);
		addSample(-4.08836781978607,51.573429107666);
		addSample(-4.08836245536804,51.5734183788299);
		addSample(-4.08836245536804,51.5734183788299);
		addSample(-4.08834636211395,51.5734076499938);
		addSample(-4.08834636211395,51.5734076499938);
		addSample(-4.08833026885986,51.5733915567398);
		addSample(-4.08833026885986,51.5733915567398);
		addSample(-4.08833026885986,51.5733808279037);
		addSample(-4.08833026885986,51.5733808279037);
		addSample(-4.08832490444183,51.5733700990676);
		addSample(-4.08832490444183,51.5733700990676);
		addSample(-4.0883195400238,51.5733540058135);
		addSample(-4.0883195400238,51.5733540058135);
		addSample(-4.08830344676971,51.5733379125595);
		addSample(-4.08830344676971,51.5733379125595);
		addSample(-4.08828735351562,51.5733218193054);
		addSample(-4.08828735351562,51.5733218193054);
		addSample(-4.08828198909759,51.5733110904693);
		addSample(-4.08828198909759,51.5733110904693);
		addSample(-4.08826053142547,51.5733003616333);
		addSample(-4.08826053142547,51.5733003616333);
		addSample(-4.08824980258941,51.5732842683792);
		addSample(-4.08824980258941,51.5732842683792);
		addSample(-4.08824443817138,51.5732735395431);
		addSample(-4.08824443817138,51.5732735395431);
		addSample(-4.08822834491729,51.573262810707);
		addSample(-4.08822834491729,51.573262810707);
		addSample(-4.08821761608123,51.573246717453);
		addSample(-4.08821761608123,51.573246717453);
		addSample(-4.08820688724517,51.5732306241989);
		addSample(-4.08820688724517,51.5732306241989);
		addSample(-4.08819079399108,51.5732198953628);
		addSample(-4.08819079399108,51.5732198953628);
		addSample(-4.08818006515502,51.5732091665267);
		addSample(-4.08818006515502,51.5732091665267);
		addSample(-4.08816933631896,51.5731930732727);
		addSample(-4.08816933631896,51.5731930732727);
		addSample(-4.08815860748291,51.5731769800186);
		addSample(-4.08815860748291,51.5731769800186);
		addSample(-4.08814787864685,51.5731662511825);
		addSample(-4.08814787864685,51.5731662511825);
		addSample(-4.08814251422882,51.5731555223464);
		addSample(-4.08814251422882,51.5731555223464);
		addSample(-4.0881210565567,51.5731340646743);
		addSample(-4.0881210565567,51.5731340646743);
		addSample(-4.08811569213867,51.5731233358383);
		addSample(-4.08811569213867,51.5731233358383);
		addSample(-4.08811032772064,51.5731126070022);
		addSample(-4.08811032772064,51.5731126070022);
		addSample(-4.08809959888458,51.5731072425842);
		addSample(-4.08809959888458,51.5731072425842);
		addSample(-4.08808887004852,51.5730965137481);
		addSample(-4.08808887004852,51.5730965137481);
		addSample(-4.08807277679443,51.573080420494);
		addSample(-4.08807277679443,51.573080420494);
		addSample(-4.08806204795837,51.5730643272399);
		addSample(-4.08806204795837,51.5730643272399);
		addSample(-4.08805131912231,51.5730535984039);
		addSample(-4.08805131912231,51.5730535984039);
		addSample(-4.08804059028625,51.5730428695678);
		addSample(-4.08804059028625,51.5730428695678);
		addSample(-4.08802986145019,51.5730267763137);
		addSample(-4.08802986145019,51.5730267763137);
		addSample(-4.08801913261413,51.5730160474777);
		addSample(-4.08801913261413,51.5730160474777);
		addSample(-4.08800840377807,51.5730053186416);
		addSample(-4.08800840377807,51.5730053186416);
		addSample(-4.08800303936004,51.5729892253875);
		addSample(-4.08800303936004,51.5729892253875);
		addSample(-4.08798694610595,51.5729731321334);
		addSample(-4.08798694610595,51.5729731321334);
		addSample(-4.08797621726989,51.5729624032974);
		addSample(-4.08797621726989,51.5729624032974);
		addSample(-4.0879601240158,51.5729463100433);
		addSample(-4.0879601240158,51.5729463100433);
		addSample(-4.08794403076171,51.5729302167892);
		addSample(-4.08794403076171,51.5729302167892);
		addSample(-4.08792793750762,51.5729141235351);
		addSample(-4.08792793750762,51.5729141235351);
		addSample(-4.08792257308959,51.572903394699);
		addSample(-4.08792257308959,51.572903394699);
		addSample(-4.08791720867156,51.572892665863);
		addSample(-4.08791720867156,51.572892665863);
		addSample(-4.08790647983551,51.5728765726089);
		addSample(-4.08790647983551,51.5728765726089);
		addSample(-4.08789575099945,51.5728658437728);
		addSample(-4.08789575099945,51.5728658437728);
		addSample(-4.08789038658142,51.5728551149368);
		addSample(-4.08789038658142,51.5728551149368);
		addSample(-4.08787965774536,51.5728497505187);
		addSample(-4.08787965774536,51.5728497505187);
		addSample(-4.08787965774536,51.5728497505187);
		addSample(-4.08787965774536,51.5728497505187);
		addSample(-4.0878689289093,51.5728551149368);
		addSample(-4.0878689289093,51.5728551149368);
		addSample(-4.08785283565521,51.5728604793548);
		addSample(-4.08785283565521,51.5728604793548);
		addSample(-4.08784210681915,51.5728712081909);
		addSample(-4.08784210681915,51.5728712081909);
		addSample(-4.087815284729,51.5728765726089);
		addSample(-4.087815284729,51.5728765726089);
		addSample(-4.08779382705688,51.572887301445);
		addSample(-4.08779382705688,51.572887301445);
		addSample(-4.08777236938476,51.572892665863);
		addSample(-4.08777236938476,51.572892665863);
		addSample(-4.08775091171264,51.572903394699);
		addSample(-4.08775091171264,51.572903394699);
		addSample(-4.08772945404052,51.5729087591171);
		addSample(-4.08772945404052,51.5729087591171);
		addSample(-4.08770263195037,51.5729194879531);
		addSample(-4.08770263195037,51.5729194879531);
		addSample(-4.08768117427825,51.5729248523712);
		addSample(-4.08768117427825,51.5729248523712);
		addSample(-4.08767044544219,51.5729355812072);
		addSample(-4.08767044544219,51.5729355812072);
		addSample(-4.08764898777008,51.5729409456253);
		addSample(-4.08764898777008,51.5729409456253);
		addSample(-4.08762753009796,51.5729463100433);
		addSample(-4.08762753009796,51.5729463100433);
		addSample(-4.08761143684387,51.5729516744613);
		addSample(-4.08761143684387,51.5729516744613);
		addSample(-4.0876168012619,51.5729624032974);
		addSample(-4.0876168012619,51.5729624032974);
		addSample(-4.08760607242584,51.5729677677154);
		addSample(-4.08760607242584,51.5729677677154);
		addSample(-4.08762216567993,51.5729784965515);
		addSample(-4.08762216567993,51.5729784965515);
		addSample(-4.08763289451599,51.5729892253875);
		addSample(-4.08763289451599,51.5729892253875);
		addSample(-4.08763825893402,51.5729999542236);
		addSample(-4.08763825893402,51.5729999542236);
		addSample(-4.08763825893402,51.5730106830596);
		addSample(-4.08763825893402,51.5730106830596);
		addSample(-4.08762753009796,51.5730160474777);
		addSample(-4.08762753009796,51.5730160474777);
		addSample(-4.0876168012619,51.5730214118957);
		addSample(-4.0876168012619,51.5730214118957);
		addSample(-4.08760070800781,51.5730267763137);
		addSample(-4.08760070800781,51.5730267763137);
		addSample(-4.08757925033569,51.5730321407318);
		addSample(-4.08757925033569,51.5730321407318);
		addSample(-4.08755779266357,51.5730428695678);
		addSample(-4.08755779266357,51.5730428695678);
		addSample(-4.08754169940948,51.5730482339859);
		addSample(-4.08754169940948,51.5730482339859);
		addSample(-4.08752024173736,51.5730535984039);
		addSample(-4.08752024173736,51.5730535984039);
		addSample(-4.08750414848327,51.5730589628219);
		addSample(-4.08750414848327,51.5730589628219);
		addSample(-4.08747732639312,51.5730643272399);
		addSample(-4.08747732639312,51.5730643272399);
		addSample(-4.087455868721,51.573075056076);
		addSample(-4.087455868721,51.573075056076);
		addSample(-4.08743977546691,51.573080420494);
		addSample(-4.08743977546691,51.573080420494);
		addSample(-4.08742368221282,51.5730857849121);
		addSample(-4.08742368221282,51.5730857849121);
		addSample(-4.08741295337677,51.5730857849121);
		addSample(-4.08741295337677,51.5730857849121);
		addSample(-4.08740758895874,51.573080420494);
		addSample(-4.08740758895874,51.573080420494);
		addSample(-4.08740222454071,51.573075056076);
		addSample(-4.08740222454071,51.573075056076);
		addSample(-4.08739149570465,51.5730643272399);
		addSample(-4.08739149570465,51.5730643272399);
		addSample(-4.08738076686859,51.5730535984039);
		addSample(-4.08738076686859,51.5730535984039);
		addSample(-4.08737003803253,51.5730482339859);
		addSample(-4.08737003803253,51.5730482339859);
		addSample(-4.08735930919647,51.5730482339859);
		addSample(-4.08735930919647,51.5730482339859);
		addSample(-4.08735930919647,51.5730535984039);
		addSample(-4.08735930919647,51.5730535984039);
		addSample(-4.08735394477844,51.5730535984039);
		addSample(-4.08735394477844,51.5730535984039);
		addSample(-4.08735394477844,51.5730535984039);
		addSample(-4.08735394477844,51.5730535984039);
		addSample(-4.08735394477844,51.5730535984039);
		addSample(-4.08735394477844,51.5730535984039);
		addSample(-4.08734858036041,51.5730535984039);
		addSample(-4.08734858036041,51.5730535984039);
		addSample(-4.08733785152435,51.5730589628219);
		addSample(-4.08733785152435,51.5730589628219);
		addSample(-4.08731639385223,51.5730643272399);
		addSample(-4.08731639385223,51.5730643272399);
		addSample(-4.08729493618011,51.573075056076);
		addSample(-4.08729493618011,51.573075056076);
		addSample(-4.08727884292602,51.573080420494);
		addSample(-4.08727884292602,51.573080420494);
		addSample(-4.08726274967193,51.5730911493301);
		addSample(-4.08726274967193,51.5730911493301);
		addSample(-4.08724665641784,51.5730965137481);
		addSample(-4.08724665641784,51.5730965137481);
		addSample(-4.08724665641784,51.5730965137481);
		addSample(-4.08724665641784,51.5730965137481);
		addSample(-4.08725202083587,51.5731018781661);
		addSample(-4.08725202083587,51.5731018781661);
		addSample(-4.0872573852539,51.5731018781661);
		addSample(-4.0872573852539,51.5731018781661);
		addSample(-4.0872573852539,51.5731018781661);
		addSample(-4.0872573852539,51.5731018781661);
		addSample(-4.08726274967193,51.5731072425842);
		addSample(-4.08726274967193,51.5731072425842);
		addSample(-4.08727347850799,51.5731233358383);
		addSample(-4.08727347850799,51.5731233358383);
		addSample(-4.08727884292602,51.5731340646743);
		addSample(-4.08727884292602,51.5731340646743);
		addSample(-4.08728957176208,51.5731501579284);
		addSample(-4.08728957176208,51.5731501579284);
		addSample(-4.08730030059814,51.5731608867645);
		addSample(-4.08730030059814,51.5731608867645);
		addSample(-4.0873110294342,51.5731769800186);
		addSample(-4.0873110294342,51.5731769800186);
		addSample(-4.08731639385223,51.5731877088546);
		addSample(-4.08731639385223,51.5731877088546);
		addSample(-4.08732712268829,51.5732091665267);
		addSample(-4.08732712268829,51.5732091665267);
		addSample(-4.08733785152435,51.5732252597808);
		addSample(-4.08733785152435,51.5732252597808);
		addSample(-4.08734858036041,51.5732359886169);
		addSample(-4.08734858036041,51.5732359886169);
		addSample(-4.0873646736145,51.573257446289);
		addSample(-4.0873646736145,51.573257446289);
		addSample(-4.08738076686859,51.5732681751251);
		addSample(-4.08738076686859,51.5732681751251);
		addSample(-4.08738613128662,51.5732842683792);
		addSample(-4.08738613128662,51.5732842683792);
		addSample(-4.08740222454071,51.5732949972152);
		addSample(-4.08740222454071,51.5732949972152);
		addSample(-4.08741295337677,51.5733057260513);
		addSample(-4.08741295337677,51.5733057260513);
		addSample(-4.08742368221282,51.5733164548873);
		addSample(-4.08742368221282,51.5733164548873);
		addSample(-4.08743977546691,51.5733271837234);
		addSample(-4.08743977546691,51.5733271837234);
		addSample(-4.08745050430297,51.5733379125595);
		addSample(-4.08745050430297,51.5733379125595);
		addSample(-4.08746123313903,51.5733486413955);
		addSample(-4.08746123313903,51.5733486413955);
		addSample(-4.08747732639312,51.5733647346496);
		addSample(-4.08747732639312,51.5733647346496);
		addSample(-4.08748269081115,51.5733808279037);
		addSample(-4.08748269081115,51.5733808279037);
		addSample(-4.08749878406524,51.5733915567398);
		addSample(-4.08749878406524,51.5733915567398);
		addSample(-4.08750414848327,51.5733969211578);
		addSample(-4.08750414848327,51.5733969211578);
		addSample(-4.0875095129013,51.5734076499938);
		addSample(-4.0875095129013,51.5734076499938);
		addSample(-4.08751487731933,51.5734183788299);
		addSample(-4.08751487731933,51.5734183788299);
		addSample(-4.08752024173736,51.573434472084);
		addSample(-4.08752024173736,51.573434472084);
		addSample(-4.08753633499145,51.5734452009201);
		addSample(-4.08753633499145,51.5734452009201);
		addSample(-4.08754169940948,51.5734559297561);
		addSample(-4.08754169940948,51.5734559297561);
		addSample(-4.08754706382751,51.5734612941741);
		addSample(-4.08754706382751,51.5734612941741);
		addSample(-4.08754706382751,51.5734666585922);
		addSample(-4.08754706382751,51.5734666585922);
		addSample(-4.08753097057342,51.5734720230102);
		addSample(-4.08753097057342,51.5734720230102);
		addSample(-4.08751487731933,51.5734720230102);
		addSample(-4.08751487731933,51.5734720230102);
		addSample(-4.08747732639312,51.5734827518463);
		addSample(-4.08747732639312,51.5734827518463);
		addSample(-4.08746123313903,51.5734881162643);
		addSample(-4.08746123313903,51.5734881162643);
		addSample(-4.08745050430297,51.5734934806823);
		addSample(-4.08745050430297,51.5734934806823);
		addSample(-4.08743977546691,51.5734988451004);
		addSample(-4.08743977546691,51.5734988451004);
		addSample(-4.08742904663085,51.5735042095184);
		addSample(-4.08742904663085,51.5735042095184);
		addSample(-4.08741295337677,51.5735042095184);
		addSample(-4.08741295337677,51.5735042095184);
		addSample(-4.08739686012268,51.5735095739364);
		addSample(-4.08739686012268,51.5735095739364);
		addSample(-4.08738613128662,51.5735203027725);
		addSample(-4.08738613128662,51.5735203027725);
		addSample(-4.08737003803253,51.5735256671905);
		addSample(-4.08737003803253,51.5735256671905);
		addSample(-4.08738613128662,51.5735417604446);
		addSample(-4.08738613128662,51.5735417604446);
		addSample(-4.08739686012268,51.5735524892807);
		addSample(-4.08739686012268,51.5735524892807);
		addSample(-4.08740758895874,51.5735632181167);
		addSample(-4.08740758895874,51.5735632181167);
		addSample(-4.08741831779479,51.5735739469528);
		addSample(-4.08741831779479,51.5735739469528);
		addSample(-4.08742368221282,51.5735900402069);
		addSample(-4.08742368221282,51.5735900402069);
		addSample(-4.08743441104888,51.5736061334609);
		addSample(-4.08743441104888,51.5736061334609);
		addSample(-4.08743977546691,51.573616862297);
		addSample(-4.08743977546691,51.573616862297);
		addSample(-4.087455868721,51.5736275911331);
		addSample(-4.087455868721,51.5736275911331);
		addSample(-4.08746659755706,51.5736383199691);
		addSample(-4.08746659755706,51.5736383199691);
		addSample(-4.08748269081115,51.5736490488052);
		addSample(-4.08748269081115,51.5736490488052);
		addSample(-4.08748805522918,51.5736597776412);
		addSample(-4.08748805522918,51.5736597776412);
		addSample(-4.08749878406524,51.5736705064773);
		addSample(-4.08749878406524,51.5736705064773);
		addSample(-4.0875095129013,51.5736812353134);
		addSample(-4.0875095129013,51.5736812353134);
		addSample(-4.08752024173736,51.5736919641494);
		addSample(-4.08752024173736,51.5736919641494);
		addSample(-4.08754706382751,51.5737080574035);
		addSample(-4.08754706382751,51.5737080574035);
		addSample(-4.08755242824554,51.5737187862396);
		addSample(-4.08755242824554,51.5737187862396);
		addSample(-4.0875631570816,51.5737295150756);
		addSample(-4.0875631570816,51.5737295150756);
		addSample(-4.08757388591766,51.5737402439117);
		addSample(-4.08757388591766,51.5737402439117);
		addSample(-4.08758997917175,51.5737509727478);
		addSample(-4.08758997917175,51.5737509727478);
		addSample(-4.08760070800781,51.5737617015838);
		addSample(-4.08760070800781,51.5737617015838);
		addSample(-4.08760607242584,51.5737724304199);
		addSample(-4.08760607242584,51.5737724304199);
		addSample(-4.08762216567993,51.5737831592559);
		addSample(-4.08762216567993,51.5737831592559);
		addSample(-4.08762216567993,51.573793888092);
		addSample(-4.08762216567993,51.573793888092);
		addSample(-4.08763289451599,51.5738046169281);
		addSample(-4.08763289451599,51.5738046169281);
		addSample(-4.08764362335205,51.5738153457641);
		addSample(-4.08764362335205,51.5738153457641);
		addSample(-4.08765435218811,51.5738314390182);
		addSample(-4.08765435218811,51.5738314390182);
		addSample(-4.08766508102416,51.5738475322723);
		addSample(-4.08766508102416,51.5738475322723);
		addSample(-4.08767580986022,51.5738528966903);
		addSample(-4.08767580986022,51.5738528966903);
		addSample(-4.08768653869628,51.5738689899444);
		addSample(-4.08768653869628,51.5738689899444);
		addSample(-4.08769726753234,51.5738797187805);
		addSample(-4.08769726753234,51.5738797187805);
		addSample(-4.0877079963684,51.5738850831985);
		addSample(-4.0877079963684,51.5738850831985);
		addSample(-4.08771872520446,51.5738958120346);
		addSample(-4.08771872520446,51.5738958120346);
		addSample(-4.08772408962249,51.5738958120346);
		addSample(-4.08772408962249,51.5738958120346);
		addSample(-4.08773481845855,51.5738958120346);
		addSample(-4.08773481845855,51.5738958120346);
		addSample(-4.0877616405487,51.5738850831985);
		addSample(-4.0877616405487,51.5738850831985);
		addSample(-4.08779382705688,51.5738797187805);
		addSample(-4.08779382705688,51.5738797187805);
		addSample(-4.08780992031097,51.5738743543624);
		addSample(-4.08780992031097,51.5738743543624);
		addSample(-4.08784210681915,51.5738636255264);
		addSample(-4.08784210681915,51.5738636255264);
		addSample(-4.08786356449127,51.5738528966903);
		addSample(-4.08786356449127,51.5738528966903);
		addSample(-4.08787965774536,51.5738421678543);
		addSample(-4.08787965774536,51.5738421678543);
		addSample(-4.08787429332733,51.5738368034362);
		addSample(-4.08787429332733,51.5738368034362);
		addSample(-4.0878689289093,51.5738314390182);
		addSample(-4.0878689289093,51.5738314390182);
		addSample(-4.08785283565521,51.5738099813461);
		addSample(-4.08785283565521,51.5738099813461);
		addSample(-4.08784210681915,51.573793888092);
		addSample(-4.08784210681915,51.573793888092);
		addSample(-4.08783137798309,51.5737777948379);
		addSample(-4.08783137798309,51.5737777948379);
		addSample(-4.087815284729,51.5737617015838);
		addSample(-4.087815284729,51.5737617015838);
		addSample(-4.08780455589294,51.5737456083297);
		addSample(-4.08780455589294,51.5737456083297);
		addSample(-4.08778309822082,51.5737348794937);
		addSample(-4.08778309822082,51.5737348794937);
		addSample(-4.0877616405487,51.5737187862396);
		addSample(-4.0877616405487,51.5737187862396);
		addSample(-4.08774554729461,51.5737026929855);
		addSample(-4.08774554729461,51.5737026929855);
		addSample(-4.08776700496673,51.5736973285675);
		addSample(-4.08776700496673,51.5736973285675);
		addSample(-4.08778846263885,51.5736865997314);
		addSample(-4.08778846263885,51.5736865997314);
		addSample(-4.08780455589294,51.5736758708953);
		addSample(-4.08780455589294,51.5736758708953);
		addSample(-4.08782601356506,51.5736758708953);
		addSample(-4.08782601356506,51.5736758708953);
		addSample(-4.08785283565521,51.5736758708953);
		addSample(-4.08785283565521,51.5736758708953);
		addSample(-4.0878689289093,51.5736705064773);
		addSample(-4.0878689289093,51.5736705064773);
		addSample(-4.08789575099945,51.5736597776412);
		addSample(-4.08789575099945,51.5736597776412);
		addSample(-4.08791184425354,51.5736490488052);
		addSample(-4.08791184425354,51.5736490488052);
		addSample(-4.08793330192565,51.5736383199691);
		addSample(-4.08793330192565,51.5736383199691);
		addSample(-4.08795475959777,51.573622226715);
		addSample(-4.08795475959777,51.573622226715);
		addSample(-4.08798158168792,51.573616862297);
		addSample(-4.08798158168792,51.573616862297);
		addSample(-4.08800840377807,51.573611497879);
		addSample(-4.08800840377807,51.573611497879);
		addSample(-4.08802986145019,51.5736061334609);
		addSample(-4.08802986145019,51.5736061334609);
		addSample(-4.08806204795837,51.5735900402069);
		addSample(-4.08806204795837,51.5735900402069);
		addSample(-4.08808350563049,51.5735846757888);
		addSample(-4.08808350563049,51.5735846757888);
		addSample(-4.08811032772064,51.5735739469528);
		addSample(-4.08811032772064,51.5735739469528);
		addSample(-4.08813178539276,51.5735685825347);
		addSample(-4.08813178539276,51.5735685825347);
		addSample(-4.08816397190093,51.5735578536987);
		addSample(-4.08816397190093,51.5735578536987);
		addSample(-4.08818542957305,51.5735524892807);
		addSample(-4.08818542957305,51.5735524892807);
		addSample(-4.08820688724517,51.5735417604446);
		addSample(-4.08820688724517,51.5735417604446);
		addSample(-4.08822298049926,51.5735310316085);
		addSample(-4.08822298049926,51.5735310316085);
		addSample(-4.08824980258941,51.5735256671905);
		addSample(-4.08824980258941,51.5735256671905);
		addSample(-4.08824980258941,51.5735363960266);
		addSample(-4.08824980258941,51.5735363960266);
		addSample(-4.0882658958435,51.5735471248626);
		addSample(-4.0882658958435,51.5735471248626);
		addSample(-4.08828198909759,51.5735632181167);
		addSample(-4.08828198909759,51.5735632181167);
		addSample(-4.08829271793365,51.5735739469528);
		addSample(-4.08829271793365,51.5735739469528);
		addSample(-4.08830344676971,51.5735900402069);
		addSample(-4.08830344676971,51.5735900402069);
		addSample(-4.08831417560577,51.5736061334609);
		addSample(-4.08831417560577,51.5736061334609);
		addSample(-4.08832490444183,51.573622226715);
		addSample(-4.08832490444183,51.573622226715);
		addSample(-4.08834099769592,51.5736329555511);
		addSample(-4.08834099769592,51.5736329555511);
		addSample(-4.08835172653198,51.5736490488052);
		addSample(-4.08835172653198,51.5736490488052);
		addSample(-4.08836245536804,51.5736597776412);
		addSample(-4.08836245536804,51.5736597776412);
		addSample(-4.08837854862213,51.5736544132232);
		addSample(-4.08837854862213,51.5736544132232);
		addSample(-4.08839464187622,51.5736544132232);
		addSample(-4.08839464187622,51.5736544132232);
		addSample(-4.08841609954833,51.5736544132232);
		addSample(-4.08841609954833,51.5736544132232);
		addSample(-4.08843755722045,51.5736436843872);
		addSample(-4.08843755722045,51.5736436843872);
		addSample(-4.08845901489257,51.5736383199691);
		addSample(-4.08845901489257,51.5736383199691);
		addSample(-4.08847510814666,51.5736329555511);
		addSample(-4.08847510814666,51.5736329555511);
		addSample(-4.08850193023681,51.573622226715);
		addSample(-4.08850193023681,51.573622226715);
		addSample(-4.08851265907287,51.573622226715);
		addSample(-4.08851265907287,51.573622226715);
		addSample(-4.0885180234909,51.573616862297);
		addSample(-4.0885180234909,51.573616862297);
		addSample(-4.08849120140075,51.573622226715);
		addSample(-4.08849120140075,51.573622226715);
		addSample(-4.08849120140075,51.573616862297);
		addSample(-4.08849120140075,51.573616862297);
		addSample(-4.08850193023681,51.573622226715);
		addSample(-4.08850193023681,51.573622226715);
		addSample(-4.08850193023681,51.573622226715);
		addSample(-4.08850193023681,51.573622226715);
		
	}

	private void addSample(double x, double y) {
		if(realExtent == null) {
			realExtent = new RectF((float)x, (float)y, (float)x, (float)y);
		}
		realExtent.union((float)x, (float)y);
        points.add(new PointF((float)x, (float)y));		
	}
}
