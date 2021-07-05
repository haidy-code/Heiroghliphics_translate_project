import 'package:flutter/material.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/placesScreenView.dart';
import 'package:http/http.dart' as http;
import 'package:flutter_module/screens/capturingScreen/captureingScreenView.dart';


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
      home: PlacesScreen(), // CaptureingScreen
      routes: {
        // When navigating to the "/" route, build the FirstScreen widget.
        // '/': (context) => PlacesScreen(),
        // When navigating to the "/second" route, build the SecondScreen widget.
        '/CaptureingScreen': (context) => CaptureingScreen(),
      },
    );
  }
}

// FIXME: i am extra
class TestNow extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Container(
            child: Center(
                child: ElevatedButton(
          onPressed: () async {
            var url = Uri.parse(
                'https://hieroglyphics-recognition-api.herokuapp.com/');
            var response = await http.get(url);
            print('Response status: ${response.statusCode}');
            print('Response body: ${response.body}');
          },
          child: Text("get data from API"),
        ))),
      ),
    );
  }
}
