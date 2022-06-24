from django.db import models

# Create your models here.
# 상하 관계: File - Sentence - Chunk

# class File(models.Model):
#     file = models.FileField()

class Sentence(models.Model):
    # file = models.ForeignKey(File, on_delete=models.CASCADE, null = True)
    # before = models.CharField(max_length=200, null = False)
    # after = models.CharField(max_length=55, null = True)
    # pronunciation = models.CharField(max_length=55, null = True)
    input = models.CharField(max_length=200, null = False)

class Chunk(models.Model):
    sentence = models.ForeignKey(Sentence, on_delete=models.CASCADE, null = False)
    before = models.CharField(max_length=30)
    after = models.CharField(max_length=55)
    pronunciation = models.CharField(max_length=55, null = True)
 