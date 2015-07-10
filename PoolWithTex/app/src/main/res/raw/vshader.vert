attribute vec4 vPosition;
attribute vec4 inColor;

uniform mat4 modelView;

varying vec4 vColor;
void main(){
    vColor = inColor;
    gl_Position = modelView * vPosition;
    gl_PointSize = 10.0;
}