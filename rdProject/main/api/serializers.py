from dataclasses import field
from rest_framework import serializers
from main.models import Sentence, Chunk

class SentenceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Sentence
        fields = '__all__'

class ChunkSerializer(serializers.ModelSerializer):
    class Meta:
        model = Chunk
        fields = ['before', 'after', 'pronunciation']


# class FileSerializer(serializers.ModelSerializer): 
#     class Meta:
#         model = File
#         fields = '__all__'