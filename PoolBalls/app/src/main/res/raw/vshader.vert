attribute vec4 vPosition;

uniform mat4 modelView;

void main(){
    gl_Position = modelView * vPosition;
    gl_PointSize = 10.0;
}