from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework.authtoken.serializers import AuthTokenSerializer

class SignInApiView(APIView):
    def post(request, self):
        serializer = AuthTokenSerializer(data = request.data)
        # username과 password가 correct한지 체크
        serializer.is_valid(raise_exception=True)
        user = serializer.validated_data['user']