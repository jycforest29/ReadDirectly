from django.shortcuts import render, redirect
from googletrans import Translator
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status

# Create your views here.

class translateApiView(APIView):
    def get(self, request):
        translator = Translator()
        result = translator.translate('안녕하세요.')
        print(result.text)
        return Response('board init complete', status = status.HTTP_201_CREATED)

