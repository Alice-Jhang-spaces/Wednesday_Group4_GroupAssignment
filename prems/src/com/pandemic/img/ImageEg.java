/*
这是一个Java程序，主要用于在图形界面上展示一张图片。下面是代码的功能解释：

1. **包声明和导入**: 程序首先声明了包名 `com.yiyuan.img` 并导入了必要的类库，包括Swing组件和AWT库。
2. **ImageEg类定义**: 定义了一个名为 `ImageEg` 的类，该类继承自 `JPanel`，是Swing的一个基础组件，用于绘制图形界面。
3. **成员变量**: 类中定义了两个成员变量 `image` 和 `image1`。其中 `image` 是 `Image` 类型，用于存储加载的图片；`image1` 是 `ImageIcon` 类型，用于加载图片并可能提供额外的功能，比如图片加载状态。
4. **构造方法**: 在构造方法中，调用了父类的构造方法并设置了组件的透明性。然后通过 `ImageEg.class.getResource("01.png")` 加载了一张图片，并将其存储在 `image1` 中。最后，从 `image1` 中获取图片并存储在 `image` 中。
5. **paintComponent方法**: 重写了 `paintComponent` 方法，该方法在JPanel需要绘制时被调用。在这个方法中，首先设置了背景颜色为白色。然后检查图片是否有效，如果有效则根据窗口的大小调整图片的大小并绘制到窗口中。
6. **main方法**: 是程序的入口点。创建了一个JFrame窗口，设置了其大小，然后添加了 `ImageEg` 组件到窗口中，并设置窗口为可见。

简而言之，这个程序的主要功能是创建一个窗口并在窗口中显示一张图片。图片的加载和显示是通过Swing和AWT库完成的。
*/

package com.pandemic.img;
import javax.swing.*;
import java.awt.*;
public class ImageEg  extends JPanel {
    private Image image;
    private ImageIcon image1;
    public ImageEg() {
        super();
        setOpaque(true);
        image1 = new ImageIcon(ImageEg.class.getResource("01.png"));
        image=image1.getImage();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        if (image!= null) {
            int height = image.getHeight(this);
            int width = image.getWidth(this);
            if (height != -1 && height > getHeight())
                height = getHeight();
            if (width != -1 && width > getWidth())
                width = getWidth();
            int x = (int) (((double) (getWidth() - width)) / 2.0);
            int y = (int) (((double) (getHeight() - height)) / 2.0);
            g.drawImage(image, x, y, width, height, this);
        }
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(1000, 500);
        ImageEg pI=new ImageEg();
        frame.add(pI);
        frame.setVisible(true);
    }
}
