import 'package:flutter/material.dart';
import 'package:flutter_module/screens/translationScreen/translationScreenView.dart';
import 'package:camera/camera.dart' as cameraPlugin;
import 'package:http/http.dart' as http;
import 'package:nuts_activity_indicator/nuts_activity_indicator.dart';

class LoadingScreen extends StatelessWidget {
  double screenWidth, screenHeight;
  cameraPlugin.XFile image;
  LoadingScreen({this.image});
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight = MediaQuery.of(context).size.height;
    Future.delayed(Duration(seconds: 8)).then((value) {
      // TODO: i will navigate from here
      Navigator.of(context).push(MaterialPageRoute(
          builder: (context) => TranslationScreen(
                image: image,
              )));
    });
    return Scaffold(
        body: Container(
            child: Stack(
      children: [
        // the eye
        Positioned(
            top: 0.22 * screenHeight,
            width: screenWidth,
            child: Center(
                child: Container(
              height: screenHeight * 0.245,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  // the image
                  Image.asset("assets/images/loadingScreen/wait_we_see_you.png",
                      width: screenHeight * 0.11,
                      height: screenHeight * 0.1,
                      fit: BoxFit.contain),
                  // title
                  Text("Please wait",
                      style: TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: screenHeight * 0.03)),
                  // subtitle
                  Text("photo is under processing",
                      style: TextStyle(fontSize: screenHeight * 0.023)),
                ],
              ),
            ))),
        // activity indicator
        Positioned(
          top: screenHeight * 0.53,
          width: screenWidth,
          child: Center(
            child: NutsActivityIndicator(
              tickCount: 8,
              endRatio: 1,
              startRatio: 1.4,
              relativeWidth: 1.8,
              radius: screenWidth * 0.8 > screenHeight * 0.04
                  ? screenHeight * 0.04
                  : screenHeight * 0.06,
              animationDuration: Duration(seconds: 3),
            ),
          ),
        ),
      ],
    )));
  }
}

/// https://pub.dev/packages/http for the post method

Future getApiData() async {
  var url = Uri.parse('https://hieroglyphics-recognition-api.herokuapp.com/');
  var response = await http.get(url);
  print('Response status: ${response.statusCode}');
  print('Response body: ${response.body}');
}
