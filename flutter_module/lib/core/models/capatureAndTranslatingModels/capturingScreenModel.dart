import 'package:camera/camera.dart';
import 'package:flutter/cupertino.dart';

class CapturingScreenModel with ChangeNotifier {
  bool isDataLoaded = false;
  CameraController cameraController;
  void dataIsLoaded(){
    isDataLoaded = true ;
    notifyListeners();
  }
}
