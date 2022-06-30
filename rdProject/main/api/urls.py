from django.urls import path
from .views import chunkApiView, sentenceApiView

urlpatterns = [
    path('chunk/<str:dest>', chunkApiView.as_view(), name = 'translate-chunk'),
    path('sentence/<str:dest>', sentenceApiView.as_view(), name = 'translate-sentence'),
]
  