from django.db import models

# Create your models here.

class Sentence(models.Model):
    before = models.CharField(max_length=30, null = False)
    dest = models.CharField(max_length=3, null = False)
    after = models.CharField(max_length=100, null = True)
    pronunciation = models.CharField(max_length=100, null = True)

class Chunk(models.Model):
    sentence = models.ForeignKey(Sentence, on_delete=models.CASCADE, null = False)
    before = models.CharField(max_length=30)
    after = models.CharField(max_length=100)
    pronunciation = models.CharField(max_length=100, null = True)
 