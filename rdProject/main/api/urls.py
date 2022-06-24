from django.urls import path
from .views import sentenceApiView

urlpatterns = [
    path('sentence/<str:dest>', sentenceApiView.as_view(), name = 'translate-sentence'),
    # path('file/<str:dest>', fileApiView.as_view(), name = 'translate-file'),
]
 