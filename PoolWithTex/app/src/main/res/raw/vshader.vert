attribute vec4 vPosition;

attribute vec2 inTextureCoord;

uniform mat4 modelView;

varying vec2 vTextureCoord;
void main(){
    vTextureCoord = inTextureCoord;
    gl_Position = modelView * vPosition;
    gl_PointSize = 10.0;
}