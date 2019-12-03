from ezgraphics import GraphicsWindow

Vindu = GraphicsWindow(200, 200)
Lerret = Vindu.canvas()

Lerret.setColor("red")
Lerret.drawOval(0, 0, 200, 200)

Vindu.wait()
