public interface NewFrame {
    
    void newFrameComplete();
    void newFrameWrong();
    void newFrameWrongPass();

}
    // public void newFrame()
    // {
    //     JFrame f = new JFrame();
    //     Container cp = f.getContentPane();
    //     cp.setLayout(new FlowLayout());
    //     JLabel l = new JLabel("Complete.");
    //     l.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
    //     cp.setBackground(Color.GREEN);
    //     cp.add(l);
    //     f.setSize(230, 80);
    //     f.setVisible(true);
    //     f.setLocationRelativeTo(null);
    // }
    // public void newFrameWrong()
    // {
    //     JFrame f = new JFrame();
    //     Container cp = f.getContentPane();
    //     cp.setLayout(new FlowLayout());
    //     JLabel l = new JLabel("Warning.");
    //     l.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
    //     cp.setBackground(Color.RED);
    //     cp.add(l);
    //     f.setSize(230, 80);
    //     f.setVisible(true);
    //     f.setLocationRelativeTo(null);
    //     //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // }