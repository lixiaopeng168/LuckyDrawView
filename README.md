# LuckyDrawView
这是一个翻牌动画


翻牌抽奖动画实现
====
本动画目前只实现了调用接口动态加载数据方式，如需本地实现方式请自行修改或者私我。方式在最下面
----
###先看一下效果:<br/>
![](https://img-blog.csdn.net/20180520153858920)
###设置配置代码:<br/>
'''Java
FlopTextView.Builder builder = new FlopTextView.Builder();
                builder.setmIsCustom(1).setmIsText(1).setmTextColor(Color.RED)
                        .setmTextSize(10).setmIsAnimType(1)
                        .setmPositiveBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.flop_03))
                        .setmOppositeBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.flop_04))
                        .setmText("是谁在用琵琶弹奏一曲东风破，歌声的旋律让人沉醉。今天头条新闻是高利贷3万要还800万。我只想默默的说一句，老妹，你牛啊。");
                flopTextView.setConfig(builder);
'''


