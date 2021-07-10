import 'package:flutter/material.dart';
import 'package:flutter_module/core/models/placesScreen/placesScreenModel.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class PlacesScreenLogic {
  PlacesScreenLogic({@required this.psm});
  PlacesScreenModel psm;
  Future getNeededData() async {
    var url = Uri.parse(
        'https://hieroglyphics-recognition-api.herokuapp.com/monumentalSites');
    //getHistoricalSitesImage/<string:imageName>
    var response = await http.get(url);
    print('Response status: ${response.statusCode}');
    psm.listOfData = json.decode(response.body);
    print(psm.listOfData[0]["description"]);
    if (response.statusCode == 200) {
      psm.dataLoaded();
    }
  }
}
