import 'package:flutter/material.dart';
import 'package:flutter_module/screens/capturingScreen/captureingScreenView.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/placesScreenView.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'GP_App',
      theme: ThemeData(),
      home: PlacesScreen(), // PlacesScreen
      routes: {
        // When navigating to the "/" route, build the FirstScreen widget.
        // '/': (context) => PlacesScreen(),
        // When navigating to the "/second" route, build the SecondScreen widget.
        '/CaptureingScreen': (context) => CaptureingScreen(),
      },
    );
  }
}
