import 'package:flutter/material.dart';

class FullDescriptionScreen extends StatelessWidget {
  double screenWidth, screenHeight;
  final String fullDescriptionString;
  FullDescriptionScreen({@required this.fullDescriptionString});
  @override
  Widget build(BuildContext context) {
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;
    screenWidth = MediaQuery.of(context).size.width;
    return SafeArea(
      child: Scaffold(
        body: Container(
          child: Stack(
            children: [
              // go back
              Positioned(
                  top: screenHeight * 0.03,
                  left: screenWidth * 0.05,
                  child: InkWell(
                    onTap: () {
                      Navigator.of(context).pop();
                    },
                    child: Row(
                      children: [
                        Icon(
                          Icons.arrow_back_ios,
                          color: Colors.black,
                          size: screenHeight * 0.03,
                        ),
                        Text(
                          "Full Description",
                          style: TextStyle(
                              color: Colors.black,
                              fontSize: screenHeight * 0.025,
                              fontWeight: FontWeight.bold),
                        )
                      ],
                    ),
                  )),
              // string
              Positioned(
                  top: screenHeight * 0.1,
                  child: Container(
                    width: screenWidth,
                    height: screenHeight * 0.9,
                    child: Theme(
                      data: ThemeData(
                        accentColor: Color(0x66ffffff)
                      ),
                                          child: SingleChildScrollView(
                          child: Container(
                              padding: EdgeInsets.all(10),
                              child: Text(fullDescriptionString))),
                    ),
                  ))
            ],
          ),
        ),
      ),
    );
  }
}
