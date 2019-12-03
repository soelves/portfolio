from ezgraphics import GraphicsWindow

win = GraphicsWindow(200, 200)
can = win.canvas()

teller = 0
x_pos = 10
stoerrelse = 20

while teller < 9:
    can.drawOval(x_pos, 100, stoerrelse, stoerrelse)
    x_pos += 5
    teller += 1
    stoerrelse += 5

win.wait()
