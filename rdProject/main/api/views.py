from httpx import StatusCode
from rest_framework.views import APIView
from rest_framework.response import Response
from .serializers import SentenceSerializer, ChunkSerializer

from main.models import Sentence, Chunk 

# Create your views here.

def translate(before, dest):
    from googletrans import Translator

    # variables
    translator = Translator() 
    sentenceSplit = before.split(' ')

    sentenceResult = translator.translate(before, dest = dest)
    sentence = Sentence.objects.create(dest = dest, before = before, after = sentenceResult.text,pronunciation = sentenceResult.pronunciation)

    for i in sentenceSplit:
        chunkResult = translator.translate(i, dest = dest)
        Chunk.objects.create(sentence = sentence, before = i, after = chunkResult.text,pronunciation = chunkResult.pronunciation)
    return sentence


# 단어 여러개
class chunkApiView(APIView):

    def post(self, request, dest):
        serializer = SentenceSerializer(data = request.data)

        if serializer.is_valid():
            # serializer.save()
            before = request.data.get('before')
            try:
                sentence = Sentence.objects.get(before = before, dest = dest)
            except Sentence.DoesNotExist:
                sentence = translate(before, dest)
            chunks = Chunk.objects.filter(sentence = sentence)
            chunkSerializer = ChunkSerializer(chunks, many = True)
            # res = [[(k, v) for k, v in sentenceSerializer.data.items()], chunkSerializer.data]
            return Response(chunkSerializer.data)
        return Response(serializer.errors, status = StatusCode.BAD_REQUEST)

# 문장 1개
class sentenceApiView(APIView):

    def post(self, request, dest):
        serializer = SentenceSerializer(data = request.data)

        if serializer.is_valid():
            # serializer.save()
            before = request.data.get('before')
            try:
                sentence = Sentence.objects.get(before = before, dest = dest)
            except Sentence.DoesNotExist:
                return Response(serializer.errors, status = StatusCode.BAD_REQUEST)

            sentenceSerializer = SentenceSerializer(sentence, many = False)
            return Response(sentenceSerializer.data)
        return Response(serializer.errors, status = StatusCode.BAD_REQUEST)

