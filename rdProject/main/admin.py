from django.contrib import admin
from .models import Sentence, Chunk

# Register your models here.

admin.site.register(Sentence)
admin.site.register(Chunk)