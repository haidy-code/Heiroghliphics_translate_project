import 'package:flutter/material.dart';
import 'dart:math' as math;
import 'dart:core';

// ignore: must_be_immutable
class BottomNavigationBarPainted extends StatelessWidget {
  double screenWidth, screenHeight;
  List<String> icons;
  List<String> texts;
  List<Function> functions;
  List<Color> iconsColors;
  BottomNavigationBarPainted({
    @required this.icons,
    @required this.texts,
    @required this.iconsColors,
    @required this.functions,
  });
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight = MediaQuery.of(context).size.height;
    return Container(
      height: screenHeight * 0.164 + 5,
      width: screenWidth,
      color: Color(0x00000000),
      child: Stack(
        children: [
          //the white bg
          Align(
            alignment: Alignment.bottomCenter,
            child: Container(
              width:screenWidth ,
              height: screenHeight * 0.114+5,
              color: Colors.white,
            ),
          ),
          // firts shadow
          Positioned(
            top: screenHeight * 0.05,
            child: Container(
                height: screenHeight * 0.114,
                width: screenWidth,
                child: CustomPaint(
                    painter: PaintedBottomNavigationBarView(
                        strokeWidth: 4,
                        color: Color(0x0f4a4b4d),
                        screenHeight: screenHeight,
                        screenWidth: screenWidth))),
          ),
          // second shadow
          Positioned(
            top: screenHeight * 0.05 + 2,
            child: Container(
                height: screenHeight * 0.114,
                width: screenWidth,
                child: CustomPaint(
                    painter: PaintedBottomNavigationBarView(
                        strokeWidth: 3,
                        color: Color(0x174a4b4d),
                        screenHeight: screenHeight,
                        screenWidth: screenWidth))),
          ),
          // last one
          Positioned(
            bottom: 0,
            child: Container(
                height: screenHeight * 0.114,
                width: screenWidth,
                child: CustomPaint(
                    painter: PaintedBottomNavigationBarView(
                        color: Color(0xffffffff),
                        screenHeight: screenHeight,
                        screenWidth: screenWidth))),
          ),
          // the button 1
          Positioned(
              bottom: 0,
              left: 0,
              child: Container(
                height: screenHeight * 0.114,
                width: screenWidth * 0.4,
                child: Center(
                    child: InkWell(
                  onTap: () {
                    functions[0]();
                  },
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Image.asset(
                        icons[0],
                        width: screenHeight * 0.033,
                        height: screenHeight * 0.033,
                        color: iconsColors[0],
                        fit: BoxFit.contain,
                      ),
                      SizedBox(
                        height: screenHeight * 0.01,
                      ),
                      Text(
                        texts[0],
                        style: TextStyle(color: iconsColors[0]),
                      )
                    ],
                  ),
                )),
              )),
          // camera
          Positioned(
              top: 0,
              width: screenWidth,
              child: Container(
                width: screenHeight,
                height: screenHeight * 0.1,
                child: Center(
                  child: Container(
                    width: screenHeight * 0.1,
                    height: screenHeight * 0.1,
                    child: FloatingActionButton(
                      backgroundColor: Color(0xffffd600),
                      child: Center(
                        child: Image.asset(
                          icons[1],
                          width: screenHeight * 0.05,
                          height: screenHeight * 0.05,
                          color: iconsColors[1],
                          fit: BoxFit.contain,
                        ),
                      ),
                      onPressed: () {
                        functions[1]();
                      },
                    ),
                  ),
                ),
              )),
          
          // the button  3
          Positioned(
              bottom: 0,
              right: 0,
              child: Container(
                height: screenHeight * 0.114,
                width: screenWidth * 0.4,
                child: Center(
                    child: InkWell(
                  onTap: () {
                    functions[2]();
                  },
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Image.asset(
                        icons[2],
                        width: screenHeight * 0.035,
                        height: screenHeight * 0.035,
                        color: iconsColors[2],
                        fit: BoxFit.contain,
                      ),
                      SizedBox(
                        height: screenHeight * 0.01,
                      ),
                      Text(
                        texts[1],
                        style: TextStyle(color: iconsColors[2]),
                      )
                    ],
                  ),
                )),
              )),
        ],
      ),
    );
  }
}

class PaintedBottomNavigationBarView extends CustomPainter {
  double screenWidth, screenHeight, strokeWidth;
  PaintedBottomNavigationBarView(
      {@required this.color,
      this.strokeWidth,
      @required this.screenHeight,
      @required this.screenWidth});
  final Color color;
  @override
  void paint(Canvas canvas, Size size) {
    // the size is 0.1 from screen height
    double width = size.width, height = size.height;
    final paint = Paint()
      ..style = PaintingStyle.stroke
      ..strokeWidth = strokeWidth ?? 1.0
      ..color = color;
    final path = Path();

    path.moveTo(0, 0);
    path.lineTo(width * 0.33, 0);
    path.arcTo(
        Rect.fromLTWH(
            width * 0.33 - width * 0.044, 0, width * 0.088, width * 0.077),
        degreeToRadian(270),
        degreeToRadian(90),
        true);
    path.arcTo(
        Rect.fromLTWH(
            width * 0.33 + width * 0.044,
            0 - screenHeight * 0.05 + width * 0.0385,
            width * 0.252,
            screenHeight * 0.1),
        degreeToRadian(0),
        degreeToRadian(180),
        true);
    path.arcTo(
        Rect.fromLTWH(
            width * 0.67 - width * 0.044, 0, width * 0.088, width * 0.077),
        degreeToRadian(180),
        degreeToRadian(90),
        true);
    path.moveTo(width * 0.67, 0);
    path.lineTo(width, 0);
    canvas.drawPath(path, paint);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    // implement shouldRepaint
    return true;
  }
}

double degreeToRadian(double value) {
  return value / 180 * math.pi;
}
