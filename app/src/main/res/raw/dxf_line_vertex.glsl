precision mediump float;

uniform mat4 u_PMatrix;
uniform mat4 u_VMatrix;
uniform mat4 u_MMatrix;

attribute vec3 a_Position;


void main() {

//    v_Position = vec3(u_MMatrix * vec4(a_Position, 1.0));
//    v_Normal = mat3(transpose(inverse(u_MMatrix))) * a_Normal;
    gl_Position = u_PMatrix * u_VMatrix * u_MMatrix * vec4(a_Position, 1.0);
//    m_Normal =
//    gl_Position =  u_MVPMatrix * a_Position;

}

