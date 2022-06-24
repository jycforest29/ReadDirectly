from httpx import StatusCode
from rest_framework.views import APIView
from rest_framework.response import Response
from .serializers import SentenceSerializer, ChunkSerializer

from main.models import Sentence, Chunk 

# Create your views here.

def translate(sentence, dest):
    from googletrans import Translator

    # variables
    translator = Translator() 
    inputSplit = sentence.input.split(' ')

    whole = translator.translate(sentence.input, dest = dest)
    Chunk.objects.create(sentence = sentence, before = sentence.input, after = whole.text, pronunciation = whole.pronunciation)

    if len(inputSplit) != 1:
        # textSplit단위로 translate
        for i in inputSplit:
            result = translator.translate(i, dest = dest)
            Chunk.objects.create(sentence = sentence, before = i, after = result.text,pronunciation = result.pronunciation)


class sentenceApiView(APIView):

    # 텍스트 입력받음 안녕하세요
    def post(self, request, dest):
        serializer = SentenceSerializer(data = request.data)
        
        if serializer.is_valid():
            serializer.save()
            sentence = Sentence.objects.filter(input = request.data.get('input'))[0]

            translate(sentence, dest)
            chunks = Chunk.objects.filter(sentence = sentence)
            serializer = ChunkSerializer(chunks, many = True)
            return Response(serializer.data)
        return Response(serializer.errors, status = StatusCode.BAD_REQUEST)

# class fileApiView(APIView):
    
#     def get(self, file, dest):
#         result = []
#         # 파일 업로드
#         with open(file, 'r', encoding = "utf-8") as f:
#             lines = f.read().splitlines()
#             for i in range(len(lines)):
#                 translate(lines[i], dest)
#                 chunks = Chunk.objects.filter(sentence = lines[i])
#                 result.append(ChunkSerializer(chunks, many = True))
#         return Response(result)
    
#     # 파일 입력받음
#     def post(self, request, dest):
#         serializer = FileSerializer(data = request.data)
#         if serializer.is_valid():
#             if File.objects.get(file = serializer.data):
#                 pass
#             else:
#                 serializer.save()
#             self.get(serializer.data, dest)
#         return Response(serializer.errors, status = StatusCode.BAD_REQUEST)
