uniform mat4 u_MVPMatrix;
uniform mat4 u_MVMatrix;

attribute vec3 a_Normal;
attribute vec4 a_Position;
attribute vec2 a_TexCoordinate;


varying vec3 v_Position;
varying vec3 v_Normal;
varying vec2 v_TexCoordinate;


void main() {

//    v_Position = vec3(u_MVPMatrix * a_Position);
    v_TexCoordinate = a_TexCoordinate;
    v_Normal = vec3(u_MVPMatrix * vec4(a_Normal, 0.0)); // the normal's orientation
    gl_Position =  u_MVPMatrix * a_Position;
    v_Position = gl_Position.xyz / gl_Position.w;
}

//    v_Position = vec3(u_MVMatrix * a_Position);
//    v_Color = a_Color;
//    v_TexCoordinate = a_TexCoordinate;
//    v_Normal = vec3(u_MVMatrix * vec4(a_Normal, 0.0)); // the normal's orientation
    //     v_Normal = a_Normal;
//    gl_Position = u_MVPMatrix * a_Position;
