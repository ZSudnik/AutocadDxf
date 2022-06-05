precision mediump float;
uniform mat4 u_MVPMatrix;
uniform mat4 u_MVMatrix;
uniform vec4 u_Color;

attribute vec4 a_Position;
attribute vec3 a_Normal;

varying vec3 v_Position;
varying vec4 v_Color;
varying vec3 v_Normal;

void main() {

    //////    v_Position = vec3(u_MVPMatrix * a_Position);
    v_Color = u_Color;
//    if( a_IsTwoSide == 1){
//        v_Normal = vec3(u_MVMatrix * vec4(a_Normal, 0.0));// the dobry transparent
//    }else{
        v_Normal = vec3(u_MVMatrix * vec4(a_Normal, 0.0));// the dobry transparent
//    }
    gl_Position =  u_MVPMatrix * a_Position;
    v_Position = gl_Position.xyz / gl_Position.w;
}

